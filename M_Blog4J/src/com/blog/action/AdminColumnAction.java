package com.blog.action;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import com.blog.Globals;
import com.blog.bean.PageView;
import com.blog.entity.BlogColumn;
import com.blog.entity.Friend;
import com.blog.entity.Message;
import com.blog.service.BlogColumnService;

public class AdminColumnAction extends BaseAction {
	
	private BlogColumnService blogColumnServiceBean;
	
	public String showAllColumnByPage(){
		String currentPage = request.getParameter("currentPage");
		if(currentPage == null){
			currentPage = "1";
		}
		PageView<BlogColumn> pageView = new PageView<BlogColumn>(Globals.MAXRESULT, Integer.parseInt(currentPage));
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("createTime", "desc");
		pageView.setQueryResult(blogColumnServiceBean.getScrollData(pageView.getFirstResult(), Globals.MAXRESULT, orderby));
		request.setAttribute("allBlogColumn", pageView);
		return "showAllColumnByPage";
	}
	
	public String searchColumn(){
		String keyWord = request.getParameter("keyWord");
		String wherejpql = "o.columnName like ?1";
		PageView<BlogColumn> pageView = new PageView<BlogColumn>(Globals.MAXRESULT, 1);
		keyWord = "%" + keyWord + "%";
		String[] queryParams = {keyWord}; 
		pageView.setQueryResult(blogColumnServiceBean.getScrollData(pageView.getFirstResult(), Globals.MAXRESULT,wherejpql,queryParams));
		pageView.setTotalrecord(blogColumnServiceBean.getCount());
		request.setAttribute("allBlogColumn", pageView);
		return "searchColumn";
	}
	
	public String recommendColumn(){
		String columnId = request.getParameter("columnId");
		blogColumnServiceBean.commendColumn(Integer.parseInt(columnId));
		return "recommendColumn";
	}
	
	public String cancelRecommendColumn(){
		String columnId = request.getParameter("columnId");
		blogColumnServiceBean.cancelCommend(Integer.parseInt(columnId));
		return "cancelRecommendColumn";
	}
	
	public String delColumn(){
		String columnId = request.getParameter("columnId");
		blogColumnServiceBean.delete(Integer.parseInt(columnId));
		return "delColumn";
	}

	public BlogColumnService getBlogColumnServiceBean() {
		return blogColumnServiceBean;
	}
	@Resource
	public void setBlogColumnServiceBean(BlogColumnService blogColumnServiceBean) {
		this.blogColumnServiceBean = blogColumnServiceBean;
	}
}
