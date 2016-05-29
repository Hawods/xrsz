package org.hawods.service.impl;

import org.hawods.entity.Seo;
import org.hawods.mapper.SeoMapper;
import org.hawods.service.SeoService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by hawods.pan on 2016/5/27 0025.
 */
@Service("seoServiceImpl")
public class SeoServiceImpl implements SeoService {

	@Resource
	private SeoMapper seoMapper;

	public Seo find(Seo.Type type) {
		return seoMapper.selectByType(type);
	}

	@Cacheable(value = "seo", condition = "#useCache")
	public Seo find(Seo.Type type, boolean useCache) {
		return seoMapper.selectByType(type);
	}
}