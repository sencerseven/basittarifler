package com.sencerseven.basittarifler.controller;

import com.sencerseven.basittarifler.command.CommentCommand;
import com.sencerseven.basittarifler.command.RecipeCommand;
import com.sencerseven.basittarifler.command.UsersCommand;
import com.sencerseven.basittarifler.domain.*;
import com.sencerseven.basittarifler.model.JsonResponder;
import com.sencerseven.basittarifler.service.*;
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


    public RecipeController(RecipeService recipeService,
                            RecipeStepsService recipeStepsService,
                            RecipeTipsService recipeTipsService,
                            CommentService commentService,
                            CategoryService categoryService) {
        this.recipeService = recipeService;
        this.recipeStepsService = recipeStepsService;
        this.recipeTipsService = recipeTipsService;
        this.commentService = commentService;
        this.categoryService = categoryService;
    }

    @GetMapping("{id}")
    public String indexAction(@AuthenticationPrincipal UsersCommand users,@PathVariable String id,
                              Model model) throws NumberFormatException {


        Recipe recipe = recipeService.getFindById(Long.valueOf(id));
        RecipeCommand recipeCommand = recipeService.getCommandFindById(Long.valueOf(id));
        List<RecipeSteps> recipeStepsList = recipeStepsService.getByRecipeOrderByViewRows(recipe);
        List<RecipeTips> recipeTipsList = recipeTipsService.getByRecipeOrderByViewRows(recipe);
        List<Recipe> recipeList = recipeService.getRecipeOrderByViewCountAndLimit(0, 3);
        List<Recipe> recipesUsers = recipeService.getRecipesByUsers(0, 3, recipe.getUsers());
        List<Comment> commentList = commentService.getCommentsByRecipeOrderByCreatedAtAsc(recipe);
        Set<Category> categories = categoryService.getCategoriesByMenuActive(0,10,true);


        List<Recipe> recipesPopuler = recipeService.getAllPopulerRecipe(0,3);
        recipeService.updateByUserCommand(recipe,users);

        model.addAttribute("recipe", recipe);
        model.addAttribute("recipeStepList", recipeStepsList);
        model.addAttribute("recipeTipsList", recipeTipsList);
        model.addAttribute("recipePopuler", recipeList);
        model.addAttribute("recipesUsers", recipesUsers);
        model.addAttribute("recipeComment", commentList);
        model.addAttribute("recipesPopuler", recipesPopuler);

        model.addAttribute("commentCommand", new CommentCommand());
        model.addAttribute("categories", categories);


        return "index";
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
