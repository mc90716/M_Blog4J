package com.blog;

import java.text.MessageFormat;
import java.text.ParseException;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.blog.entity.User;
import com.blog.enumeration.UserState;
import com.blog.service.UserService;
import com.blog.service.impl.UserServiceBean;
import com.blog.utils.RequestUtils;
import com.blog.utils.StringUtils;

@Service
public class UserLogManager {


	/**
	 * 表单的提交时间必须是生成表单后的0.5秒钟以上
	 */
	private final static int MIN_MS_BETWEEN_ACTION = 500;

	/**
	 * 表单的有效期是一个小时
	 */
	private final static int MAX_MS_BETWEEN_ACTION = 3600000;
	//private final static String SESSION_USER_KEY = "UserBean";
	private final static String COOKIE_UUID_KEY = "uuid";
	
	
	
	/**
	 * 用户登录，首先判断Session中是否已经存在，存在的话，直接返回。如果不存在
	 * @param request
	 * @param response
	 * @param user
	 * @param keepDays
	 * @return
	 */
	public SessionUserObject loginUser(HttpServletRequest request,
			HttpServletResponse response,User user,int keepDays){
		HttpSession session = request.getSession(false);
		if(session != null){   //会话存在，用户已经登录那么就直接返回sessionUserObject
			SessionUserObject sub = (SessionUserObject) session.getAttribute(Globals.SESSION_USER_KEY);
			if((sub != null)&&(sub.getUserId() == user.getUserId())){
				return sub;
			}
		}
		//会话不存在，那么就新建一个sessionUserObject对象并且初始化
		SessionUserObject sessionUserObject = new SessionUserObject();
		sessionUserObject.setUserId(user.getUserId());
		sessionUserObject.setUserName(user.getUserName());
		sessionUserObject.setKeepDays(keepDays);
		sessionUserObject.setUserId(user.getUserId());
		sessionUserObject.setPassWd(user.getPassWd());
		sessionUserObject.setLastAddr(request.getRemoteAddr());
		sessionUserObject.setUserState(UserState.inline);


		//讲登录信息写进Cookie
		UUID uuid = new UUID();
		uuid.uid = sessionUserObject.getUserId();
		uuid.pwdCode = sessionUserObject.getPassWd().hashCode();
		uuid.host = sessionUserObject.getLastAddr();

		String value = uuid.toString();
		RequestUtils.setCookies(request, response, COOKIE_UUID_KEY, value, (keepDays > 0) ? keepDays * 86400 : -1);

		//新建Session，并且将sessionUserObject放到Session中
		if(session == null)
			session = request.getSession(true);
		if(session != null && sessionUserObject != null){
			session.setAttribute(Globals.SESSION_USER_KEY, sessionUserObject);
		}
		return sessionUserObject;
	}


	public String logoutUser(HttpServletRequest req,HttpServletResponse res){
		SessionUserObject suo = getLoginUser(req, res);
		// 清除Cookie
		RequestUtils.setCookies(req, res, COOKIE_UUID_KEY, "", 0);

		// 清除session
		HttpSession ssn = req.getSession(false);
		if (ssn != null) {
			ssn.invalidate();
		}
		
		return suo.getUserName();
	}


	/**
	 * 获得用户的登录的SessionUserObject，如果session中不存在，则获取Cookies，然后执行自动登录
	 * @param request
	 * @param response
	 * @return
	 */
	public static SessionUserObject getLoginUser(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession(false);
		//Cookie和session同时存在的情况下才有效
		if(session != null){
			SessionUserObject user = (SessionUserObject) session.getAttribute(Globals.SESSION_USER_KEY);
			if(user != null){
				Cookie cookie = getUuidCookie(request, COOKIE_UUID_KEY);
				if(cookie != null)
					return user;
			}
		}
		return null;
	}

	private static Cookie getUuidCookie(HttpServletRequest request,String str){
		return RequestUtils.getCookie(request, str);
	}

	/**
	 * 验证客户端安全识别码
	 * 
	 * @param req
	 * @param clientId
	 * @return
	 */
	public static boolean validateClientId(HttpServletRequest req,
			String clientId) {
		return ClientID.validate(req, clientId);
	}

	/**
	 * 生成客户端安全识别码
	 * 
	 * @param req
	 * @return
	 */
	public static String generateClientId(HttpServletRequest req,
			HttpServletResponse res) {
		return ClientID.generate(req, res);
	}


	private static class UUID {
		/**
		 * 自动登录标识的加密密码 IMPORTANT: 建议修改该值后重新编译系统以保证系统的安全性 该密钥的长度必须是8的整数倍
		 */
		private final static String UUID_ENCRYPT_KEY = "1D2L3O4G546J7V83"; final static String PATTERN = "{0}_{1}@{2}";

		private final static MessageFormat parser = new MessageFormat(PATTERN);

		public int uid;
		public String host;
		public int pwdCode;

		public UUID(){}

		public String toString(){
			String uuid = MessageFormat.format(PATTERN, new String[] {
					String.valueOf(uid), String.valueOf(pwdCode), host });
			return StringUtils.encrypt(uuid, UUID_ENCRYPT_KEY);
		}
	}



	private static class ClientID{

		private final static String CLIENTID_ENCRYPT_KEY = "MLOG4J";
		private final static String PATTERN = "{0}|{1}|{2}";
		private final static MessageFormat parser = new MessageFormat(PATTERN);

		/**
		 * 生成客户端验证码
		 * @param req
		 * @param res
		 * @return
		 */
		public static String generate(HttpServletRequest req, HttpServletResponse res){
			StringBuffer code = new StringBuffer();
			Long ct = System.currentTimeMillis();
			code.append(ct);
			code.append('|');
			code.append(req.getRemoteAddr());
			code.append('|');
			String user_agent = RequestUtils.getHeader(req, Globals.USER_AGENT);
			if(user_agent == null){
				code.append(ct);
			}else{
				code.append(Math.abs(user_agent.hashCode()));
			}
			return StringUtils.encrypt(code.toString(), CLIENTID_ENCRYPT_KEY);
		}

		public static boolean validate(HttpServletRequest req, String code){
			String clientCode = StringUtils.decrypt(code, CLIENTID_ENCRYPT_KEY);
			Object[] objs = null;
			try {
				objs = parser.parse(clientCode);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String host = req.getRemoteAddr();
			if(host.equals(objs[1])){
				String user_agent = RequestUtils.getHeader(req, Globals.USER_AGENT);
				String ua = (user_agent!=null)?String.valueOf((Math.abs(user_agent.hashCode()))):null;
				if((objs[2].equals(ua)) || (objs[2].equals(objs[0]))){
					Long lt = Long.parseLong(objs[0].toString());
					Long ct = System.currentTimeMillis();
					//客户端识别码的有效时常为一个小时
					long it = lt - ct;
					if (MIN_MS_BETWEEN_ACTION < it
							&& it < MAX_MS_BETWEEN_ACTION) {
						return true;
					}
				}
			}
			return false;
		}
	}
}
