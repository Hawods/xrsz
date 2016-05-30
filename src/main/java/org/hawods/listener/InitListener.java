package org.hawods.listener;

import org.hawods.service.ConfigService;
import org.hawods.service.StaticService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.util.logging.Logger;

/**
 * Created by hawods.pan on 2016/5/27 0025.
 */
@Component("initListener")
public class InitListener implements ServletContextAware, ApplicationListener<ContextRefreshedEvent> {
    private static final Logger LOGGER = Logger.getLogger(InitListener.class.getName());

    private ServletContext servletContext;

    @Value("${system.version}")
    private String systemVersion;
    @Resource(name = "configServiceImpl")
    private ConfigService configService;
    @Resource(name = "staticServiceImpl")
    private StaticService staticService;

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (servletContext != null && contextRefreshedEvent.getApplicationContext().getParent() == null) {
            String info = "I|n|i|t|i|a|l|i|z|i|n|g| |3|3|G|O| |" + systemVersion;
            LOGGER.info(info.replace("|", ""));
            configService.init();
            staticService.generateIndex();
//			staticService.generateOther();
        }
    }

}