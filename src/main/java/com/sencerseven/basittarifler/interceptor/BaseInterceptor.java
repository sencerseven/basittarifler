package com.sencerseven.basittarifler.interceptor;

import com.sencerseven.basittarifler.command.UsersCommand;
import com.sencerseven.basittarifler.domain.Users;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Locale;

public class BaseInterceptor extends HandlerInterceptorAdapter {

    public static final String controllerDef = "Controller";
    public static final String actionDef = "Action";

    Locale locale = new Locale("en","US");



    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String controllerName ="";
        String actionName ="";
        if(handler instanceof HandlerMethod && modelAndView != null){
            HandlerMethod handlerMethod = (HandlerMethod) handler;


            controllerName = handlerMethod.getBeanType().getSimpleName();
            actionName =handlerMethod.getMethod().getName();
            if(controllerName.substring(controllerName.length() - controllerDef.length()).equals(controllerDef)){
                controllerName = controllerName.replace(controllerDef,"").toLowerCase(locale);
            }else{
                controllerName = "";
            }

            if(actionName.substring(actionName.length() - actionDef.length()).equals(actionDef)){
                actionName = actionName.replace(actionDef,"").toLowerCase(locale);
            }else{
                actionName = "";
            }
            String[] path = request.getServletPath().substring(1, request.getServletPath().length()).split("/");
            String viewUrl="";
            String jsUrl ="";
            System.out.println(path[0]);
            if(path[0].equals("admin")){
               viewUrl = "admin/" +controllerName+"/"+actionName;
               jsUrl = "/js/admin/page/"+controllerName+"/"+actionName+".js";
            }else{
                viewUrl = "page/" +controllerName+"/"+actionName;
                jsUrl = "/js/page/"+controllerName+"/"+actionName+".js";
            }

            modelAndView.addObject("view",viewUrl);
            modelAndView.addObject("js",jsUrl);
            modelAndView.addObject("controllerName",controllerName);
            modelAndView.addObject("actionName",actionName);

            UsersCommand users = null;
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(authentication != null && authentication.isAuthenticated()){
                Object principal = authentication.getPrincipal();
                if(principal instanceof UsersCommand){
                    users = (UsersCommand) principal;
                }
            }

            modelAndView.addObject("currentUser",users);


        }




    }
}
