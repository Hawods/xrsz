package org.hawods.controller.admin;

import org.apache.commons.lang3.time.DateUtils;
import org.hawods.service.CacheService;
import org.hawods.service.StaticService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hawods.pan on 2016/5/30 0030.
 */
@Controller("adminStaticController")
@RequestMapping("/admin/static")
public class StaticController extends BaseController {

    public enum GenerateType {

        index,

        article,

        goods,

        other
    }

    @Resource(name = "staticServiceImpl")
    private StaticService staticService;
    @Resource(name = "cacheServiceImpl")
    private CacheService cacheService;

    @RequestMapping(value = "/generate", method = RequestMethod.GET)
    public String generate(ModelMap model) {
        model.addAttribute("generateTypes", StaticController.GenerateType.values());
        model.addAttribute("defaultBeginDate", DateUtils.addDays(new Date(), -7));
        model.addAttribute("defaultEndDate", new Date());
        return "/admin/static/generate";
    }

    @RequestMapping(value = "/generate", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> generate(StaticController.GenerateType generateType, Long articleCategoryId, Long productCategoryId, Date beginDate, Date endDate, Integer first, Integer count) {
        long startTime = System.currentTimeMillis();
        if (beginDate != null) {
            Calendar calendar = DateUtils.toCalendar(beginDate);
            calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
            calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
            calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
            beginDate = calendar.getTime();
        }
        if (endDate != null) {
            Calendar calendar = DateUtils.toCalendar(endDate);
            calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
            calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
            calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
            endDate = calendar.getTime();
        }
        if (first == null || first < 0) {
            first = 0;
            cacheService.clear();
        }
        if (count == null || count <= 0) {
            count = 100;
        }
        int generateCount = 0;
        boolean isCompleted = true;
        switch (generateType) {
            case index:
                generateCount = staticService.generateIndex();
                break;
            case article:
                break;
            case goods:
                break;
            case other:
                generateCount = staticService.generateOther();
                break;
        }
        long endTime = System.currentTimeMillis();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("first", first);
        data.put("generateCount", generateCount);
        data.put("generateTime", endTime - startTime);
        data.put("isCompleted", isCompleted);
        return data;
    }

}
