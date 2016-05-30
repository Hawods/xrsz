package org.hawods.service;

import java.util.Map;

/**
 * Created by hawods.pan on 2016/5/30 0030.
 */
public interface StaticService {

	int generate(String templatePath, String staticPath, Map<String, Object> model);

	int generateIndex();

	int generateSitemap();

	int generateOther();

	int generateAll();

	int delete(String staticPath);

	int deleteIndex();

	int deleteOther();

}