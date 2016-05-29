package org.hawods.template.directive;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.hawods.entity.Seo;
import org.hawods.service.SeoService;
import org.hawods.util.FreeMarkerUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * Created by hawods.pan on 2016/5/27 0025.
 */
@Component("seoDirective")
public class SeoDirective extends BaseDirective {

	private static final String TYPE_PARAMETER_NAME = "type";

	private static final String VARIABLE_NAME = "seo";

	@Resource(name = "seoServiceImpl")
	private SeoService seoService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		Seo.Type type = FreeMarkerUtils.getParameter(TYPE_PARAMETER_NAME, Seo.Type.class, params);
		boolean useCache = useCache(env, params);
		Seo seo = seoService.find(type, useCache);
		setLocalVariable(VARIABLE_NAME, seo, env, body);
	}

}