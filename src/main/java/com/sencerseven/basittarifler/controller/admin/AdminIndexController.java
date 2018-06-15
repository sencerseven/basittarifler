package com.sencerseven.basittarifler.controller.admin;

import com.sencerseven.basittarifler.command.UsersCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminIndexController {

/*
    @Autowired
    @Qualifier("sessionRegistry")
    private SessionRegistry sessionRegistry;
*/
    @RequestMapping(value = {""})
    public String indexAction(){
     /*   List<Object> principals = sessionRegistry.getAllPrincipals();

        List<String> usersNamesList = new ArrayList<String>();

        for (Object principal: principals) {
            if (principal instanceof UsersCommand) {
                usersNamesList.add(((UsersCommand) principal).getUserName());
            }
        }
*/
        return "admin";
    }
}
