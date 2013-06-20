package com.blog.dataConvert;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.blog.entity.Category;

public class CategoryConvert extends StrutsTypeConverter{

	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		Category category = new Category();
		String str = values[0];
		category.setCategoryId(Integer.parseInt(str));
		return category;
	}

	@Override
	public String convertToString(Map context, Object o) {
		Category category = (Category)o;
		
		return category.getCategoryId() + "";
	}

}
