package org.hawods.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hawods on 5/26/16.
 */
@Controller
@RequestMapping("/common")
public class CommonController {
    @RequestMapping("error")
    public String error(){
        return "/main/${theme}/common/error";
    }
    @RequestMapping("resource_not_found")
    public String resourceNotFound(){
        return "/main/${theme}/common/resource_not_found";
    }
}
