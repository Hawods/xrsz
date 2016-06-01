package org.hawods;

import java.io.Serializable;

/**
 * Created by hawods on 6/1/16.
 */
public class LogConfig implements Serializable {

    public static final String CACHE_NAME = "logConfig";

    private String operation;

    private String urlPattern;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getUrlPattern() {
        return urlPattern;
    }

    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }
}
