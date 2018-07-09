package com.sencerseven.basittarifler.command;

import com.sencerseven.basittarifler.domain.Ingredient;
import com.sencerseven.basittarifler.domain.RecipeImages;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {

    private Long id;

    private String recipeTitle;

    private String recipeDescription;

    private String recipeText;

    private String difficulty;

    private Date created_at;

    private int person;

    private int portion;

    private int prepMin;

    private int cookMin;


    NutritionCommand nutritionCommand;

    List<CategoryCommand> categories = new ArrayList<>();
    List<RecipeStepsCommand> recipeSteps = new ArrayList<>();

    List<RecipeTipsCommand> recipeTipsCommands = new ArrayList<>();

    List<RecipeImagesCommand> recipeImagesCommands = new ArrayList<>();

    List<IngredientCommand> ingredientCommands = new ArrayList<>();

    CuisineCommand cuisineCommand;

}
