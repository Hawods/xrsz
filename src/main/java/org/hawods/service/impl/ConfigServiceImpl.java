package org.hawods.service.impl;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModelException;
import org.apache.commons.lang3.LocaleUtils;
import org.apache.commons.lang3.StringUtils;
import org.hawods.Setting;
import org.hawods.service.ConfigService;
import org.hawods.util.SystemUtils;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * Created by hawods.pan on 2016/5/27 0025.
 */
@Service("configServiceImpl")
public class ConfigServiceImpl implements ConfigService {

	@Value("${template.update_delay}")
	private String templateUpdateDelay;
	@Value("${message.cache_seconds}")
	private Integer messageCacheSeconds;

	@Resource(name = "freeMarkerConfigurer")
	private FreeMarkerConfigurer freeMarkerConfigurer;
	@Resource(name = "messageSource")
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;
	@Resource(name = "localeResolver")
	private FixedLocaleResolver fixedLocaleResolver;

	public void init() {
		try {
			Setting setting = SystemUtils.getSetting();
			ProxyFactory proxyFactory = new ProxyFactory(setting);
			proxyFactory.setProxyTargetClass(true);
			proxyFactory.addAdvice(new MethodBeforeAdvice() {

				public void before(Method method, Object[] args, Object target) throws Throwable {
					if (StringUtils.startsWith(method.getName(), "set")) {
						throw new UnsupportedOperationException("Operation not supported");
					}
				}

			});
			Configuration configuration = freeMarkerConfigurer.getConfiguration();
			configuration.setSharedVariable("setting", proxyFactory.getProxy());
			configuration.setSharedVariable("locale", setting.getLocale());
			configuration.setSharedVariable("theme", setting.getTheme());
			if (setting.getIsDevelopmentEnabled()) {
				configuration.setSetting("template_update_delay", "0");
				reloadableResourceBundleMessageSource.setCacheSeconds(0);
			} else {
				configuration.setSetting("template_update_delay", templateUpdateDelay);
				reloadableResourceBundleMessageSource.setCacheSeconds(messageCacheSeconds);
			}
			fixedLocaleResolver.setDefaultLocale(LocaleUtils.toLocale(setting.getLocale().toString()));
		} catch (TemplateModelException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (TemplateException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

}