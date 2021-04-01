package com.thymeleaf.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by
 *
 * @author wdc
 * @date 2021/3/8.
 */
@Controller
public class HelloController {

    @RequestMapping("/")
    public String index(ModelMap map){
        map.addAttribute("message","http://www.ityouknow.com");
        return "hello";
    }
}
