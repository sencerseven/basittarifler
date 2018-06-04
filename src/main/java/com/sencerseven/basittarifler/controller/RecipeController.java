package com.sencerseven.basittarifler.controller;

import com.sencerseven.basittarifler.command.CommentCommand;
import com.sencerseven.basittarifler.command.RecipeCommand;
import com.sencerseven.basittarifler.command.TestCommand;
import com.sencerseven.basittarifler.command.UsersCommand;
import com.sencerseven.basittarifler.domain.*;
import com.sencerseven.basittarifler.exceptions.NotFoundException;
import com.sencerseven.basittarifler.model.JsonResponder;
import com.sencerseven.basittarifler.repository.RecipeStepsRepository;
import com.sencerseven.basittarifler.service.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.JsonViewResponseBodyAdvice;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/recipe")
public class RecipeController {

    RecipeService recipeService;
    RecipeStepsService recipeStepsService;
    RecipeTipsService recipeTipsService;
    CommentService commentService;
    RecipeStatsService recipeStatsService;


    public RecipeController(RecipeService recipeService,
                            RecipeStepsService recipeStepsService,
                            RecipeTipsService recipeTipsService,
                            CommentService commentService,
                            RecipeStatsService recipeStatsService) {

        this.recipeService = recipeService;
        this.recipeStepsService = recipeStepsService;
        this.recipeTipsService = recipeTipsService;
        this.commentService = commentService;
        this.recipeStatsService = recipeStatsService;


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
        recipeStatsService.saveByUsersCommand(users,recipe);

        model.addAttribute("recipe", recipe);
        model.addAttribute("recipeStepList", recipeStepsList);
        model.addAttribute("recipeTipsList", recipeTipsList);
        model.addAttribute("recipePopuler", recipeList);
        model.addAttribute("recipesUsers", recipesUsers);
        model.addAttribute("recipeComment", commentList);

        model.addAttribute("commentCommand", new CommentCommand());


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
