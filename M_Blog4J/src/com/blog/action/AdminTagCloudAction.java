package com.blog.action;

import javax.annotation.Resource;

import com.blog.bean.QueryResult;
import com.blog.entity.TagCloud;
import com.blog.service.TagCloudService;

public class AdminTagCloudAction extends BaseAction {
	
	private TagCloudService tagCloudServiceBean;
	private TagCloud tagCloud;
	
	@Override
	public String execute() throws Exception {
		QueryResult<TagCloud> tagClouds = tagCloudServiceBean.getScrollData();
		request.setAttribute("tagClouds", tagClouds);
		return super.execute();
	}
	
	public String addTag(){
		tagCloudServiceBean.save(tagCloud);
		return "addTag";
	}
	
	public TagCloudService getTagCloudServiceBean() {
		return tagCloudServiceBean;
	}
	@Resource
	public void setTagCloudServiceBean(TagCloudService tagCloudServiceBean) {
		this.tagCloudServiceBean = tagCloudServiceBean;
	}

	public TagCloud getTagCloud() {
		return tagCloud;
	}

	public void setTagCloud(TagCloud tagCloud) {
		this.tagCloud = tagCloud;
	}
}
