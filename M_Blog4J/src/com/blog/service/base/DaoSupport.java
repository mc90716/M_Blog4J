package com.blog.service.base;

import com.blog.utils.GenericsUtils;

public abstract class DaoSupport<T> implements DAO<T>{
	protected Class<T> entityClass = GenericsUtils.getSuperClassGenricType(this.getClass());
}
