package org.hawods.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hawods on 5/26/16.
 */
@Controller
public class IndexController {
    @RequestMapping("/admin/index")
    public String index(){
        return "/admin/index";
    }
}
