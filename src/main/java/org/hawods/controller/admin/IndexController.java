package org.hawods.controller.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.LoaderUtil;
import org.hawods.entity.Seo;
import org.hawods.mapper.SeoMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by hawods on 5/26/16.
 */
@Controller("adminIndexController")
@RequestMapping("/admin/index")
public class IndexController {
    private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    @Resource
    private SeoMapper seoMapper;

    @RequestMapping
    public String index() {
        Seo seo = seoMapper.selectSeo(1);
        logger.debug(seo.getId() + ":" + seo.getType() + "|" + seo.getTitle());
        logger.warn(seo.getId() + ":" + seo.getType() + "|" + seo.getTitle());
        logger.error(seo.getId() + ":" + seo.getType() + "|" + seo.getTitle());
        return "/admin/index";
    }
}
