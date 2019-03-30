package com.sencerseven.basittarifler.controller.admin;

import com.sencerseven.basittarifler.command.RecipeCommand;
import com.sencerseven.basittarifler.command.UsersCommand;
import com.sencerseven.basittarifler.converter.RecipeToRecipeCommandConverter;
import com.sencerseven.basittarifler.domain.Category;
import com.sencerseven.basittarifler.domain.Cuisine;
import com.sencerseven.basittarifler.domain.Level;
import com.sencerseven.basittarifler.domain.Recipe;
import com.sencerseven.basittarifler.service.CategoryService;
import com.sencerseven.basittarifler.service.CuisineService;
import com.sencerseven.basittarifler.service.RecipeService;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("admin/recipe")
public class AdminRecipeController {

    RecipeService recipeService;
    CategoryService categoryService;
    CuisineService cuisineService;

    RecipeToRecipeCommandConverter recipeToRecipeCommandConverter;

    public AdminRecipeController(RecipeService recipeService, CategoryService categoryService, CuisineService cuisineService, RecipeToRecipeCommandConverter recipeToRecipeCommandConverter) {
        this.recipeService = recipeService;
        this.categoryService = categoryService;
        this.cuisineService = cuisineService;
        this.recipeToRecipeCommandConverter = recipeToRecipeCommandConverter;
    }

    @RequestMapping(value = {""})
    public String indexAction(Model model){
        Page<Recipe> recipePage = recipeService.findAllByOrderByCreatedAtDesc(0,10000);

        model.addAttribute("recipes",recipePage);

        return "admin";
    }

    @GetMapping(value = "add")
    public String addRecipeAction(Model model){

        Set<Category> categorySet = categoryService.getCategories();
        RecipeCommand recipeCommand = new RecipeCommand();
        List<Cuisine> cuisineList = cuisineService.findAll();
        model.addAttribute("recipeCommand",recipeCommand);
        model.addAttribute("categories",categorySet);
        model.addAttribute("cuisines",cuisineList);
        model.addAttribute("difficulty",Level.values());

        return "admin";
    }

    @PostMapping(value = "add")
    public String addRecipeAction(RecipeCommand recipeCommand,
                                  @AuthenticationPrincipal UsersCommand usersCommand,
                                  BindingResult bindingResult){

        if(bindingResult.hasErrors()){

            return "admin";
        }

        RecipeCommand recipeCommandResult = recipeService.saveRecipeCommand(recipeCommand,usersCommand);

        return "redirect:/admin/recipe/";
    }


    @GetMapping(value = "edit/{id}")
    public String editRecipeAction(Model model, @PathVariable Long id){

        Recipe recipe = recipeService.getFindById(id);

        RecipeCommand recipeCommand = recipeToRecipeCommandConverter.convert(recipe);

        Set<Category> categorySet = categoryService.getCategories();
        List<Cuisine> cuisineList = cuisineService.findAll();

        model.addAttribute("recipeCommand",recipeCommand);
        model.addAttribute("categories",categorySet);
        model.addAttribute("cuisines",cuisineList);
        model.addAttribute("difficulty",Level.values());


        return "admin";
    }

}
