package org.hawods.controller.admin;

import com.fasterxml.jackson.databind.deser.Deserializers;
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
public class IndexController extends BaseController {
    @RequestMapping
    public String index() {
        return "/admin/index";
    }
}
