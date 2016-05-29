package org.hawods.service;

import org.hawods.entity.Seo;

/**
 * Created by hawods.pan on 2016/5/27 0025.
 */
public interface SeoService {

	Seo find(Seo.Type type);

	Seo find(Seo.Type type, boolean useCache);

}