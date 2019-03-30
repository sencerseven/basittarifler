package com.sencerseven.basittarifler.controller;

import com.sencerseven.basittarifler.domain.Category;
import com.sencerseven.basittarifler.domain.Posts;
import com.sencerseven.basittarifler.domain.Recipe;
import com.sencerseven.basittarifler.service.CategoryService;
import com.sencerseven.basittarifler.service.PostsService;
import com.sencerseven.basittarifler.service.RecipeService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;


@Controller
@RequestMapping("/recipelist")
public class RecipeListController {

    RecipeService recipeService;
    CategoryService categoryService;
    PostsService postsService;

    public RecipeListController(RecipeService recipeService, CategoryService categoryService, PostsService postsService) {
        this.recipeService = recipeService;
        this.categoryService = categoryService;
        this.postsService = postsService;
    }

    @RequestMapping()
    public String indexAction(Model model, @RequestParam(value = "page",required = false,defaultValue = "1")int id) throws Exception {
        Page<Recipe> recipeList = recipeService.findAllByOrderByCreatedAtDesc(id-1,6);
        List<Recipe> recipePopuler = recipeService.getAllPopulerRecipe(0,3);
        Set<Category> categories = categoryService.getCategoriesByMenuActive(0,10,true);
        Set<Posts> posts =  postsService.findAllByPage(0,5,true);

        model.addAttribute("recipePage",recipeList);
        model.addAttribute("recipeCount",recipeService.countAllBy());
        model.addAttribute("recipePopular",recipePopuler);
        model.addAttribute("categories",categories);
        model.addAttribute("posts",posts);


        return "index";
    }


    @GetMapping("{id}/{category}")
    public String searchAction(Model model,@PathVariable("id") String catId,@PathVariable("category")String categoryUrl,
    @RequestParam(value = "page",required = false,defaultValue = "1")int id){
        Set<Category> categories = categoryService.defineCategoryParentOrSubsForPage(Long.valueOf(catId),categoryUrl);
        Page<Recipe> recipeList = recipeService.findRecipeByCategoriesInOrderByCreatedAtDesc(id-1,6,categories);

        List<Recipe> recipePopuler = recipeService.getAllPopulerRecipe(0,3);
        Set<Category> allCategories = categoryService.getCategoriesByMenuActive(0,10,true);


        model.addAttribute("recipePage",recipeList);
        model.addAttribute("recipeCount",recipeService.countAllBy());
        model.addAttribute("recipePopular",recipePopuler);
        model.addAttribute("categories",allCategories);

        return "index";
    }

    @GetMapping("search")
    public String tagsearchAction(Model model,@RequestParam(value="tags")String tags, @RequestParam(value = "page",required = false,defaultValue = "1")int id){
        Page<Recipe> recipeList = recipeService.findRecipeByTagsContaining(id-1,10,tags);
        List<Recipe> recipePopuler = recipeService.getAllPopulerRecipe(0,3);
        Set<Category> categories = categoryService.getCategoriesByMenuActive(0,10,true);

        model.addAttribute("recipePage",recipeList);
        model.addAttribute("recipeCount",recipeService.countAllBy());
        model.addAttribute("recipePopular",recipePopuler);
        model.addAttribute("categories",categories);


        return "index";
    }

}
