package org.hawods.service;

/**
 * Created by hawods.pan on 2016/5/27 0025.
 */
public interface CacheService {

	String getDiskStorePath();

	int getCacheSize();

	void clear();

}