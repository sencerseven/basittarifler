package com.sencerseven.basittarifler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @RequestMapping("profile")
    public String profileAction(){

        return "index";
    }

}
