package org.hawods;

/**
 * Created by hawods.pan on 2016/5/25 0025.
 */
public final class CommonAttributes {

    public static final String[] DATE_PATTERNS = new String[]{"yyyy", "yyyy-MM", "yyyyMM", "yyyy/MM", "yyyy-MM-dd", "yyyyMMdd", "yyyy/MM/dd", "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss", "yyyy/MM/dd HH:mm:ss"};

    public static final String SYS_CONFIG_XML_PATH = "/sys_config.xml";
    public static final String EHCACHE_XML_PATH = "/ehcache.xml";

    private CommonAttributes() {
    }

}