package com.wdc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by wdc on 2021/3/30 10:34
 */
@Controller
public class HomeController {

    @RequestMapping("/hello")
    public String hello(Model model, @RequestParam(value = "name",required = false,
            defaultValue = "World") String name){
        model.addAttribute("name",name);
        return "hello";
    }
}
