package com.sencerseven.basittarifler.controller;

import com.sencerseven.basittarifler.command.CategoryCommand;
import com.sencerseven.basittarifler.command.UsersCommand;
import com.sencerseven.basittarifler.domain.Category;
import com.sencerseven.basittarifler.domain.Recipe;
import com.sencerseven.basittarifler.service.CategoryService;
import com.sencerseven.basittarifler.service.RecipeService;
import com.sencerseven.basittarifler.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@Controller
public class IndexController {

    RecipeService recipeService;


    CategoryService categoryService;

    @Autowired
    UsersService usersService;

    public IndexController(RecipeService recipeService,CategoryService categoryService) {
        this.recipeService = recipeService;
        this.categoryService = categoryService;
    }

    @RequestMapping(value = {"/"})
    public String indexAction(Model model){
        List<Recipe> recipes = recipeService.getLimitRecipes(PageRequest.of(0,3));
        List<Recipe> populerRecipes = recipeService.getAllPopulerRecipe(0,3);
        Set<CategoryCommand> menuList =  categoryService.getCategoriesByMainPageStatus(0,4,true);

        model.addAttribute("recipes",recipes);
        model.addAttribute("pupulerRecipes",populerRecipes);
        model.addAttribute("menuList",menuList);

        return ("index");

    }

    @GetMapping(value = {"/register"})
    public String registerAction(Model model){

        UsersCommand usersCommand = new UsersCommand();

        model.addAttribute("Users",usersCommand);

        return ("index");
    }

    @PostMapping(value = {"/register"})
    public String register(UsersCommand usersCommand){

        usersCommand.setActive(1);

        usersService.saveUsersCommand(usersCommand);

        return ("redirect:/");
    }

    @RequestMapping(value = {"/login"})
    public String loginAction(@RequestParam(name = "error",required = false) String error,Model model){
        model.addAttribute("error");

        return ("index");
   }

}
