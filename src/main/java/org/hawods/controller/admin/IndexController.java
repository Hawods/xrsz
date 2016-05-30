package org.hawods.controller.admin;

import org.hawods.entity.Seo;
import org.hawods.mapper.SeoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by hawods on 5/26/16.
 */
@Controller("adminIndexController")
@RequestMapping("/admin/index")
public class IndexController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private SeoMapper seoMapper;

    @RequestMapping
    public String index() {
        try {
            Seo seo = seoMapper.selectSeo(1);
            logger.debug(seo.getId() + ":" + seo.getType() + "|" + seo.getTitle());
            logger.warn(seo.getId() + ":" + seo.getType() + "|" + seo.getTitle());
            logger.error(seo.getId() + ":" + seo.getType() + "|" + seo.getTitle());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/admin/index";
    }
}
