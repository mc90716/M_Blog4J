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
	 * �����ύʱ����������ɱ����0.5��������
	 */
	private final static int MIN_MS_BETWEEN_ACTION = 500;

	/**
	 * ������Ч����һ��Сʱ
	 */
	private final static int MAX_MS_BETWEEN_ACTION = 3600000;
	//private final static String SESSION_USER_KEY = "UserBean";
	private final static String COOKIE_UUID_KEY = "uuid";
	
	
	
	/**
	 * �û���¼�������ж�Session���Ƿ��Ѿ����ڣ����ڵĻ���ֱ�ӷ��ء����������
	 * @param request
	 * @param response
	 * @param user
	 * @param keepDays
	 * @return
	 */
	public SessionUserObject loginUser(HttpServletRequest request,
			HttpServletResponse response,User user,int keepDays){
		HttpSession session = request.getSession(false);
		if(session != null){   //�Ự���ڣ��û��Ѿ���¼��ô��ֱ�ӷ���sessionUserObject
			SessionUserObject sub = (SessionUserObject) session.getAttribute(Globals.SESSION_USER_KEY);
			if((sub != null)&&(sub.getUserId() == user.getUserId())){
				return sub;
			}
		}
		//�Ự�����ڣ���ô���½�һ��sessionUserObject�����ҳ�ʼ��
		SessionUserObject sessionUserObject = new SessionUserObject();
		sessionUserObject.setUserId(user.getUserId());
		sessionUserObject.setUserName(user.getUserName());
		sessionUserObject.setKeepDays(keepDays);
		sessionUserObject.setUserId(user.getUserId());
		sessionUserObject.setPassWd(user.getPassWd());
		sessionUserObject.setLastAddr(request.getRemoteAddr());
		sessionUserObject.setUserState(UserState.inline);


		//����¼��Ϣд��Cookie
		UUID uuid = new UUID();
		uuid.uid = sessionUserObject.getUserId();
		uuid.pwdCode = sessionUserObject.getPassWd().hashCode();
		uuid.host = sessionUserObject.getLastAddr();

		String value = uuid.toString();
		RequestUtils.setCookies(request, response, COOKIE_UUID_KEY, value, (keepDays > 0) ? keepDays * 86400 : -1);

		//�½�Session�����ҽ�sessionUserObject�ŵ�Session��
		if(session == null)
			session = request.getSession(true);
		if(session != null && sessionUserObject != null){
			session.setAttribute(Globals.SESSION_USER_KEY, sessionUserObject);
		}
		return sessionUserObject;
	}


	public String logoutUser(HttpServletRequest req,HttpServletResponse res){
		SessionUserObject suo = getLoginUser(req, res);
		// ���Cookie
		RequestUtils.setCookies(req, res, COOKIE_UUID_KEY, "", 0);

		// ���session
		HttpSession ssn = req.getSession(false);
		if (ssn != null) {
			ssn.invalidate();
		}
		
		return suo.getUserName();
	}


	/**
	 * ����û��ĵ�¼��SessionUserObject�����session�в����ڣ����ȡCookies��Ȼ��ִ���Զ���¼
	 * @param request
	 * @param response
	 * @return
	 */
	public static SessionUserObject getLoginUser(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession(false);
		//Cookie��sessionͬʱ���ڵ�����²���Ч
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
	 * ��֤�ͻ��˰�ȫʶ����
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
	 * ���ɿͻ��˰�ȫʶ����
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
		 * �Զ���¼��ʶ�ļ������� IMPORTANT: �����޸ĸ�ֵ�����±���ϵͳ�Ա�֤ϵͳ�İ�ȫ�� ����Կ�ĳ��ȱ�����8��������
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
		 * ���ɿͻ�����֤��
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
					//�ͻ���ʶ�������Чʱ��Ϊһ��Сʱ
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
