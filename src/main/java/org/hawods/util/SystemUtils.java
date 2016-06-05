package org.hawods.util;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.ArrayConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.Configuration;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.xml.XmlConfiguration;
import org.hawods.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.Assert;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hawods.pan on 2016/5/25 0025.
 */
public final class SystemUtils {

    private static final CacheManager CACHE_MANAGER;

    private static final BeanUtilsBean BEAN_UTILS;

    static {
        final URL url = SystemUtils.class.getResource(CommonAttributes.EHCACHE_XML_PATH);
        Configuration xmlConfig = new XmlConfiguration(url);
        CACHE_MANAGER = CacheManagerBuilder.newCacheManager(xmlConfig);
        CACHE_MANAGER.init();

        ConvertUtilsBean convertUtilsBean = new ConvertUtilsBean() {
            @Override
            public String convert(Object value) {
                if (value != null) {
                    Class<?> type = value.getClass();
                    if (type.isEnum() && super.lookup(type) == null) {
                        super.register(new EnumConverter(type), type);
                    } else if (type.isArray() && type.getComponentType().isEnum()) {
                        if (super.lookup(type) == null) {
                            ArrayConverter arrayConverter = new ArrayConverter(type, new EnumConverter(type.getComponentType()), 0);
                            arrayConverter.setOnlyFirstToString(false);
                            super.register(arrayConverter, type);
                        }
                        Converter converter = super.lookup(type);
                        return ((String) converter.convert(String.class, value));
                    }
                }
                return super.convert(value);
            }

            @SuppressWarnings("rawtypes")
            @Override
            public Object convert(String value, Class clazz) {
                if (clazz.isEnum() && super.lookup(clazz) == null) {
                    super.register(new EnumConverter(clazz), clazz);
                }
                return super.convert(value, clazz);
            }

            @SuppressWarnings("rawtypes")
            @Override
            public Object convert(String[] values, Class clazz) {
                if (clazz.isArray() && clazz.getComponentType().isEnum() && super.lookup(clazz.getComponentType()) == null) {
                    super.register(new EnumConverter(clazz.getComponentType()), clazz.getComponentType());
                }
                return super.convert(values, clazz);
            }

            @SuppressWarnings("rawtypes")
            @Override
            public Object convert(Object value, Class targetType) {
                if (super.lookup(targetType) == null) {
                    if (targetType.isEnum()) {
                        super.register(new EnumConverter(targetType), targetType);
                    } else if (targetType.isArray() && targetType.getComponentType().isEnum()) {
                        ArrayConverter arrayConverter = new ArrayConverter(targetType, new EnumConverter(targetType.getComponentType()), 0);
                        arrayConverter.setOnlyFirstToString(false);
                        super.register(arrayConverter, targetType);
                    }
                }
                return super.convert(value, targetType);
            }
        };

        DateConverter dateConverter = new DateConverter();
        dateConverter.setPatterns(CommonAttributes.DATE_PATTERNS);
        convertUtilsBean.register(dateConverter, Date.class);
        BEAN_UTILS = new BeanUtilsBean(convertUtilsBean);
    }

    private SystemUtils() {
    }

    public static Setting getSetting() {
        Cache<String, Setting> cache = CACHE_MANAGER.getCache(Setting.CACHE_NAME, String.class, Setting.class);
        Setting setting = cache.get(Setting.CACHE_NAME);
        if (setting == null) {
            setting = new Setting();
            try {
                File sysConfigXml = new ClassPathResource(CommonAttributes.SYS_CONFIG_XML_PATH).getFile();
                Document document = new SAXReader().read(sysConfigXml);
                List<org.dom4j.Element> elements = document.selectNodes("/sys/setting");
                for (org.dom4j.Element element : elements) {
                    try {
                        String name = element.attributeValue("name");
                        String value = element.attributeValue("value");
                        BEAN_UTILS.setProperty(setting, name, value);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e.getMessage(), e);
                    } catch (InvocationTargetException e) {
                        throw new RuntimeException(e.getMessage(), e);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            } catch (DocumentException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
            cache.put(Setting.CACHE_NAME, setting);
        }
        return setting;
    }

    public static void setSetting(Setting setting) {
        Assert.notNull(setting);

        try {
            File sysConfigXml = new ClassPathResource(CommonAttributes.SYS_CONFIG_XML_PATH).getFile();
            Document document = new SAXReader().read(sysConfigXml);
            List<org.dom4j.Element> elements = document.selectNodes("/sys/setting");
            for (org.dom4j.Element element : elements) {
                try {
                    String name = element.attributeValue("name");
                    String value = BEAN_UTILS.getProperty(setting, name);
                    Attribute attribute = element.attribute("value");
                    attribute.setValue(value);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e.getMessage(), e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e.getMessage(), e);
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
            }

            XMLWriter xmlWriter = null;
            try {
                OutputFormat outputFormat = OutputFormat.createPrettyPrint();
                outputFormat.setEncoding("UTF-8");
                outputFormat.setIndent(true);
                outputFormat.setIndent("	");
                outputFormat.setNewlines(true);
                xmlWriter = new XMLWriter(new FileOutputStream(sysConfigXml), outputFormat);
                xmlWriter.write(document);
                xmlWriter.flush();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e.getMessage(), e);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e.getMessage(), e);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            } finally {
                try {
                    if (xmlWriter != null) {
                        xmlWriter.close();
                    }
                } catch (IOException e) {
                }
            }
            Cache<String, Setting> cache = CACHE_MANAGER.getCache(Setting.CACHE_NAME, String.class, Setting.class);
            cache.put(Setting.CACHE_NAME, setting);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (DocumentException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static List<LogConfig> getLogConfigs() {
        Cache<String, Object> cache = CACHE_MANAGER.getCache(LogConfig.CACHE_NAME, String.class, Object.class);
        String cacheKey = "logConfigs";
        List<LogConfig> logConfigs = (List<LogConfig>) cache.get(cacheKey);
        if (logConfigs == null) {
            logConfigs = new ArrayList<LogConfig>();
            try {
                File sysConfigXml = new ClassPathResource(CommonAttributes.SYS_CONFIG_XML_PATH).getFile();
                Document document = new SAXReader().read(sysConfigXml);
                List<org.dom4j.Element> elements = document.selectNodes("/sys/logConfig");
                for (org.dom4j.Element element : elements) {
                    String type = element.attributeValue("type");
                    String operation = element.attributeValue("operation");
                    String urlPattern = element.attributeValue("urlPattern");

                    LogConfig logConfig = new LogConfig();
                    logConfig.setType(type);
                    logConfig.setOperation(operation);
                    logConfig.setUrlPattern(urlPattern);
                    logConfigs.add(logConfig);
                }
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            } catch (DocumentException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
            cache.put(cacheKey, logConfigs);
        }
        return logConfigs;
    }

    public static TemplateConfig getTemplateConfig(String id) {
        Assert.hasText(id);

        Cache<String, Object> cache = CACHE_MANAGER.getCache(TemplateConfig.CACHE_NAME, String.class, Object.class);
        String cacheKey = "templateConfig_" + id;
        TemplateConfig templateConfig = (TemplateConfig) cache.get(cacheKey);
        if (templateConfig == null) {
            try {
                File sysConfigXml = new ClassPathResource(CommonAttributes.SYS_CONFIG_XML_PATH).getFile();
                Document document = new SAXReader().read(sysConfigXml);
                org.dom4j.Element element = (org.dom4j.Element) document.selectSingleNode("/sys/templateConfig[@id='" + id + "']");
                if (element != null) {
                    templateConfig = getTemplateConfig(element);
                }
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            } catch (DocumentException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
            cache.put(cacheKey, templateConfig);
        }
        return templateConfig;
    }

    public static List<TemplateConfig> getTemplateConfigs() {
        Cache<String, Object> cache = CACHE_MANAGER.getCache(TemplateConfig.CACHE_NAME, String.class, Object.class);
        String cacheKey = "templateConfigs";
        List<TemplateConfig> templateConfigs = (List<TemplateConfig>) cache.get(cacheKey);
        if (templateConfigs == null) {
            templateConfigs = new ArrayList<TemplateConfig>();
            try {
                File sysConfigXml = new ClassPathResource(CommonAttributes.SYS_CONFIG_XML_PATH).getFile();
                Document document = new SAXReader().read(sysConfigXml);
                List<org.dom4j.Element> elements = document.selectNodes("/sys/templateConfig");
                for (org.dom4j.Element element : elements) {
                    templateConfigs.add(getTemplateConfig(element));
                }
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            } catch (DocumentException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
            cache.put(cacheKey, templateConfigs);
        }
        return templateConfigs;
    }

    private static TemplateConfig getTemplateConfig(org.dom4j.Element element) {
        Assert.notNull(element);

        String id = element.attributeValue("id");
        String templatePath = element.attributeValue("templatePath");
        String staticPath = element.attributeValue("staticPath");

        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setId(id);
        templateConfig.setTemplatePath(templatePath);
        templateConfig.setStaticPath(staticPath);
        return templateConfig;
    }

}