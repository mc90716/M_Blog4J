package com.blog.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.Dao.DaoSupport;
import com.blog.entity.TagCloud;
import com.blog.service.TagCloudService;

@Service
@Transactional
public class TagCloudServiceBean extends DaoSupport<TagCloud> implements
		TagCloudService {

}
