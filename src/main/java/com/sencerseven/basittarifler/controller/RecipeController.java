package com.sencerseven.basittarifler.controller;

import com.sencerseven.basittarifler.command.CommentCommand;
import com.sencerseven.basittarifler.command.RecipeCommand;
import com.sencerseven.basittarifler.command.UsersCommand;
import com.sencerseven.basittarifler.domain.*;
import com.sencerseven.basittarifler.model.JsonResponder;
import com.sencerseven.basittarifler.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/recipe")
public class RecipeController {

    RecipeService recipeService;
    RecipeStepsService recipeStepsService;
    RecipeTipsService recipeTipsService;
    CommentService commentService;
    CategoryService categoryService;
    S3Services s3Services;
    CuisineService cuisineService;
    BKodService bKodService;

    Logger log = LoggerFactory.getLogger(RecipeController.class);


    public RecipeController(RecipeService recipeService,
                            RecipeStepsService recipeStepsService,
                            RecipeTipsService recipeTipsService,
                            CommentService commentService,
                            CategoryService categoryService,
                            S3Services s3Services,
                            CuisineService cuisineService,
                            BKodService bKodService) {

        this.recipeService = recipeService;
        this.recipeStepsService = recipeStepsService;
        this.recipeTipsService = recipeTipsService;
        this.commentService = commentService;
        this.categoryService = categoryService;
        this.s3Services = s3Services;
        this.cuisineService = cuisineService;
        this.bKodService = bKodService;
    }

    @GetMapping("{id}")
    public String indexAction(@AuthenticationPrincipal UsersCommand users,@PathVariable String id,
                              Model model) throws NumberFormatException {

        log.debug("Index Action - User -> " + users.getId());
        Recipe recipe = recipeService.getFindById(Long.valueOf(id));
        log.debug("recipe" + recipe.getId());
        RecipeCommand recipeCommand = recipeService.getCommandFindById(Long.valueOf(id));
        log.debug("recipe" + recipe.getId());
        List<RecipeSteps> recipeStepsList = recipeStepsService.getByRecipeOrderByViewRows(recipe);
        List<RecipeTips> recipeTipsList = recipeTipsService.getByRecipeOrderByViewRows(recipe);
        List<Recipe> recipeList = recipeService.getRecipeOrderByViewCountAndLimit(0, 3);
        List<Recipe> recipesUsers = recipeService.getRecipesByUsers(0, 3, recipe.getUsers());
        List<Comment> commentList = commentService.getCommentsByRecipeOrderByCreatedAtAsc(recipe);
        Set<Category> categories = categoryService.getCategoriesByMenuActive(0,10,true);
        Set<BKod> bKods = bKodService.findAllByBkod(0,10,"measurement");


        Page<Recipe> tagss = recipeService.findRecipeByTagsContaining(0,10,"oldu");

        List<Recipe> recipesPopuler = recipeService.getAllPopulerRecipe(0,3);
        recipeService.updateByUserCommand(recipe,users);

        s3Services.getUrl("recipe/"+recipe.getRecipeUrl());

        model.addAttribute("recipe", recipe);
        model.addAttribute("recipeStepList", recipeStepsList);
        model.addAttribute("recipeTipsList", recipeTipsList);
        model.addAttribute("recipePopuler", recipeList);
        model.addAttribute("recipesUsers", recipesUsers);
        model.addAttribute("recipeComment", commentList);
        model.addAttribute("recipesPopuler", recipesPopuler);
        model.addAttribute("bKods",bKods);

        model.addAttribute("commentCommand", new CommentCommand());
        model.addAttribute("categories", categories);


        return "index";
    }

    @GetMapping("add")
    public String addRecipeAction(Model model){

        Set<Category> categorySet = categoryService.getCategories();
        RecipeCommand recipeCommand = new RecipeCommand();
        List<Cuisine> cuisineList = cuisineService.findAll();
        model.addAttribute("recipeCommand",recipeCommand);
        model.addAttribute("categories",categorySet);
        model.addAttribute("cuisines",cuisineList);
        model.addAttribute("difficulty",Level.values());

        return "index";
    }

    @PostMapping(value = "add")
    public String addRecipeAction(RecipeCommand recipeCommand,
                                  @AuthenticationPrincipal UsersCommand usersCommand,
                                  BindingResult bindingResult){

        if(bindingResult.hasErrors()){

            return "index";
        }

        recipeService.saveRecipeCommand(recipeCommand,usersCommand);

        return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addcomment/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    JsonResponder commentadd(@Valid @RequestBody CommentCommand commentCommand,
                             @AuthenticationPrincipal UsersCommand users,
                             @PathVariable("id") Long recipeId,
                             BindingResult result) throws Exception {

        JsonResponder jsonResponder = new JsonResponder();

        if (result.hasErrors()) {
            jsonResponder.setStatus("FAIL");
            return jsonResponder;
        }


        Recipe recipe = recipeService.getFindById(recipeId);
        if (recipe != null && users != null) {
            CommentCommand responseCommend = commentService.saveCommentCommand(commentCommand, recipe, users);

            if (responseCommend != null) {
                jsonResponder.setStatus("SUCCESS");
                jsonResponder.setObject(responseCommend);
                return jsonResponder;
            }


        }

        return jsonResponder;
    }


}
