package org.hawods.controller.main;

import org.hawods.entity.Seo;
import org.hawods.mapper.SeoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hawods on 5/26/16.
 */
@Controller
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private SeoMapper seoMapper;

    @RequestMapping
    public String index() {
        Seo seo = seoMapper.selectSeo(1);
        return "/main/${theme}/index";
    }
}
