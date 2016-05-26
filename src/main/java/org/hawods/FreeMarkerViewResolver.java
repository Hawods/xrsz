package org.hawods;

import org.hawods.util.FreeMarkerUtils;
import org.springframework.web.servlet.view.AbstractTemplateViewResolver;
import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

/**
 * Created by hawods.pan on 2016/5/25 0025.
 */
public class FreeMarkerViewResolver extends AbstractTemplateViewResolver {

    public FreeMarkerViewResolver() {
        setViewClass(requiredViewClass());
    }

    @Override
    protected Class<FreeMarkerView> requiredViewClass() {
        return FreeMarkerView.class;
    }

    @Override
    protected AbstractUrlBasedView buildView(String viewName) throws Exception {
        return super.buildView(FreeMarkerUtils.process(viewName));
    }

}