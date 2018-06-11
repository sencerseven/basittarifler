package com.sencerseven.basittarifler.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminIndexController {


    @RequestMapping("")
    public String indexAction(){
        return "admin/index";
    }
}
