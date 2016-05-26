package org.hawods;

import org.hawods.util.FreeMarkerUtils;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

/**
 * Created by hawods.pan on 2016/5/26 0026.
 */
public class TemplateConfig implements Serializable {

    public static final String CACHE_NAME = "templateConfig";

    private String id;

    private String templatePath;

    private String staticPath;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public String getStaticPath() {
        return staticPath;
    }

    public void setStaticPath(String staticPath) {
        this.staticPath = staticPath;
    }

    public String getRealTemplatePath() {
        return getRealTemplatePath(null);
    }

    public String getRealTemplatePath(Map<String, Object> model) {
        try {
            return FreeMarkerUtils.process(getTemplatePath(), model);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (TemplateException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public String getRealStaticPath() {
        return getRealStaticPath(null);
    }

    public String getRealStaticPath(Map<String, Object> model) {
        try {
            return FreeMarkerUtils.process(getStaticPath(), model);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (TemplateException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}