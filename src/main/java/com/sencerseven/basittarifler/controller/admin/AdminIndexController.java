package com.sencerseven.basittarifler.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminIndexController {

    /*
        @Autowired
        @Qualifier("sessionRegistry")
        private SessionRegistry sessionRegistry;
    */
    @RequestMapping(value = {""})
    public String indexAction() {
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
