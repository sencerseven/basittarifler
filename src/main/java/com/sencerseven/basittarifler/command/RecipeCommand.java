package com.sencerseven.basittarifler.command;

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

    private Date created_at;

    private int person;

    private int portion;

    private int prepMin;

    private int cookMin;


    NutritionCommand nutritionCommand;

    Set<CategoryCommand> categories = new HashSet<>();
    List<RecipeStepsCommand> recipeSteps = new ArrayList<>();

    List<RecipeTipsCommand> recipeTipsCommands = new ArrayList<>();
}
