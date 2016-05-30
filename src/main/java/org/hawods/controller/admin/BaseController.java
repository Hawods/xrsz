package org.hawods.controller.admin;

import org.hawods.Message;
import org.hawods.Setting;
import org.hawods.util.SpringUtils;
import org.hawods.util.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by hawods.pan on 2016/5/30 0030.
 */
public class BaseController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected static final String ERROR_VIEW = "/admin/common/error";

    protected static final Message ERROR_MESSAGE = Message.error("admin.message.error");

    protected static final Message SUCCESS_MESSAGE = Message.success("admin.message.success");

    private static final String CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME = "constraintViolations";

    @Resource(name = "validator")
    private Validator validator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
//		binder.registerCustomEditor(Date.class, new DateEditor(true));
//		binder.registerCustomEditor(String.class, "password", new StringEditor(true));
    }

    @ExceptionHandler
    public ModelAndView exceptionHandler(Exception exception, HttpServletResponse response) {
        logger.warn("Handler execution resulted in exception", exception);
        Setting setting = SystemUtils.getSetting();
        ModelMap model = new ModelMap();
        if (setting.getIsDevelopmentEnabled()) {
            model.addAttribute("exception", exception);
        }
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return new ModelAndView(ERROR_VIEW, model);
    }

    protected boolean isValid(Object target, Class<?>... groups) {
        Assert.notNull(target);

        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(target, groups);
        if (constraintViolations.isEmpty()) {
            return true;
        }
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        requestAttributes.setAttribute(CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME, constraintViolations, RequestAttributes.SCOPE_REQUEST);
        return false;
    }

    protected boolean isValid(Collection<Object> targets, Class<?>... groups) {
        Assert.notEmpty(targets);

        for (Object target : targets) {
            if (!isValid(target, groups)) {
                return false;
            }
        }
        return true;
    }

    protected boolean isValid(Class<?> type, String property, Object value, Class<?>... groups) {
        Assert.notNull(type);
        Assert.hasText(property);

        Set<?> constraintViolations = validator.validateValue(type, property, value, groups);
        if (constraintViolations.isEmpty()) {
            return true;
        }
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        requestAttributes.setAttribute(CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME, constraintViolations, RequestAttributes.SCOPE_REQUEST);
        return false;
    }

    protected boolean isValid(Class<?> type, Map<String, Object> properties, Class<?>... groups) {
        Assert.notNull(type);
        Assert.notEmpty(properties);

        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            if (!isValid(type, entry.getKey(), entry.getValue(), groups)) {
                return false;
            }
        }
        return true;
    }

    protected String message(String code, Object... args) {
        return SpringUtils.getMessage(code, args);
    }

}