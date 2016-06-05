package org.hawods.interceptor;

import org.apache.commons.lang3.ArrayUtils;
import org.hawods.LogConfig;
import org.hawods.entity.Log;
import org.hawods.service.AdminService;
import org.hawods.service.LogService;
import org.hawods.service.MemberService;
import org.hawods.util.SystemUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by hawods on 6/1/16.
 */
public class LogInterceptor extends HandlerInterceptorAdapter {

    private static final String[] DEFAULT_IGNORE_PARAMETERS = new String[]{"password", "rePassword", "currentPassword"};

    private static AntPathMatcher antPathMatcher = new AntPathMatcher();

    private String[] ignoreParameters = DEFAULT_IGNORE_PARAMETERS;

    @Resource
    private LogService logService;
    @Resource
    private MemberService memberService;
    @Resource
    private AdminService adminService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        List<LogConfig> logConfigs = SystemUtils.getLogConfigs();
        if (logConfigs != null) {
            String path = request.getServletPath();
            for (LogConfig logConfig : logConfigs) {
                if (antPathMatcher.match(logConfig.getUrlPattern(), path)) {
                    String operation = logConfig.getOperation();
                    String operator;
                    if ("admin".equals(logConfig.getType())) {
                        operator = adminService.getCurrentUsername();
                    } else {
                        operator = memberService.getCurrentUsername();
                    }
                    String ip = request.getRemoteAddr();
                    StringBuilder parameter = new StringBuilder();
                    Map<String, String[]> parameterMap = request.getParameterMap();
                    if (parameterMap != null) {
                        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                            String parameterName = entry.getKey();
                            if (!ArrayUtils.contains(ignoreParameters, parameterName)) {
                                String[] parameterValues = entry.getValue();
                                if (parameterValues != null) {
                                    for (String parameterValue : parameterValues) {
                                        parameter.append(parameterName + " = " + parameterValue + "\n");
                                    }
                                }
                            }
                        }
                    }
                    Log log = new Log();
                    log.setType(logConfig.getType());
                    log.setOperation(operation);
                    log.setOperator(operator);
                    log.setParameter(parameter.toString());
                    log.setIp(ip);
                    logService.save(log);
                    break;
                }
            }
        }
    }

    public String[] getIgnoreParameters() {
        return ignoreParameters;
    }

    public void setIgnoreParameters(String[] ignoreParameters) {
        this.ignoreParameters = ignoreParameters;
    }
}
