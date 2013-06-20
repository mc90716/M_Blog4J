package com.blog.action;

import java.util.ArrayList;

import javax.annotation.Resource;

import com.blog.SessionUserObject;
import com.blog.entity.Article;
import com.blog.service.FavouriteService;

public class FavouriteAction extends BaseAction {
	
	private FavouriteService favouriteServiceBean;
	
	
	public String showAllFavourites(){
		SessionUserObject suo = getLoginUser(request, response);
		ArrayList<Article> favourites = favouriteServiceBean.getArticleByUserId(suo.getUserId());
		request.setAttribute("favourites", favourites);
		return "showAllFavourites";
	}
	
	public String deleteFavourite(){
		String articleId = request.getParameter("articleId");
		SessionUserObject suo = getLoginUser(request, response);
		favouriteServiceBean.delFavourite(Integer.parseInt(articleId),suo.getUserId());
		return "deleteFavourite";
	}


	public FavouriteService getFavouriteServiceBean() {
		return favouriteServiceBean;
	}

	@Resource
	public void setFavouriteServiceBean(FavouriteService favouriteServiceBean) {
		this.favouriteServiceBean = favouriteServiceBean;
	}
}
