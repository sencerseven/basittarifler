package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.RecipeCommand;
import com.sencerseven.basittarifler.domain.Recipe;
import com.sencerseven.basittarifler.functions.BasitTarifHelpers;
import com.sencerseven.basittarifler.service.CategoryService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipeConverter implements Converter<RecipeCommand, Recipe> {


    CategoryCommandToCategoryConverter categoryCommandToCategoryConverter;
    RecipeStepsCommandToRecipeStepsConverter recipeStepsCommandToRecipeStepsConverter;
    NutritionCommandToNutritionConverter nutritionCommandToNutritionConverter;
    RecipeTipsCommandToRecipeTipsConverter recipeTipsCommandToRecipeTipsConverter;
    RecipeImagesCommandToRecipeImagesConverter recipeImagesCommandToRecipeImagesConverter;
    IngredientCommandToIngredientConverter ingredientCommandToIngredientConverter;
    CategoryService categoryService;

    public RecipeCommandToRecipeConverter(CategoryCommandToCategoryConverter categoryCommandToCategoryConverter, RecipeStepsCommandToRecipeStepsConverter recipeStepsCommandToRecipeStepsConverter, NutritionCommandToNutritionConverter nutritionCommandToNutritionConverter, RecipeTipsCommandToRecipeTipsConverter recipeTipsCommandToRecipeTipsConverter, RecipeImagesCommandToRecipeImagesConverter recipeImagesCommandToRecipeImagesConverter, IngredientCommandToIngredientConverter ingredientCommandToIngredientConverter, CategoryService categoryService) {
        this.categoryCommandToCategoryConverter = categoryCommandToCategoryConverter;
        this.recipeStepsCommandToRecipeStepsConverter = recipeStepsCommandToRecipeStepsConverter;
        this.nutritionCommandToNutritionConverter = nutritionCommandToNutritionConverter;
        this.recipeTipsCommandToRecipeTipsConverter = recipeTipsCommandToRecipeTipsConverter;
        this.recipeImagesCommandToRecipeImagesConverter = recipeImagesCommandToRecipeImagesConverter;
        this.ingredientCommandToIngredientConverter = ingredientCommandToIngredientConverter;
        this.categoryService = categoryService;
    }

    @Override
    public Recipe convert(RecipeCommand source) {
        if (source == null)
            return null;

        Recipe recipe = new Recipe();

        recipe.setId(source.getId());
        recipe.setRecipeDescription(source.getRecipeDescription());
        recipe.setRecipeText(source.getRecipeText());
        recipe.setRecipeTitle(source.getRecipeTitle());
        recipe.setRecipeUrl(BasitTarifHelpers.toSlug(source.getRecipeTitle()));
        recipe.setPerson(source.getPerson());
        recipe.setPortion(source.getPortion());
        recipe.setCookMin(source.getCookMin());
        recipe.setPrepMin(source.getPrepMin());
        recipe.setCreatedAt(source.getCreated_at());

        if (source.getNutritionCommand() != null)
            recipe.addNutrition(nutritionCommandToNutritionConverter.convert(source.getNutritionCommand()));

        if (source.getCategories() != null && source.getCategories().size() > 0)
            source.getCategories().forEach(category -> recipe.getCategories().add(categoryService.getById(category.getId())));

        if (source.getRecipeSteps() != null && source.getRecipeSteps().size() > 0)
            source.getRecipeSteps().forEach(recipeSteps -> recipe.addRecipeSteps(recipeStepsCommandToRecipeStepsConverter.convert(recipeSteps)));

        if (source.getRecipeTipsCommands() != null && source.getRecipeTipsCommands().size() > 0)
            source.getRecipeTipsCommands().forEach(recipeTipsCommand -> recipe.addRecipeTips(recipeTipsCommandToRecipeTipsConverter.convert(recipeTipsCommand)));

        if(source.getRecipeImagesCommands() != null && source.getRecipeImagesCommands().size()>0){
            source.getRecipeImagesCommands().forEach(recipeImagesCommand -> recipe.addRecipeImages(recipeImagesCommandToRecipeImagesConverter.convert(recipeImagesCommand)));
        }

        if(source.getIngredientCommands() != null && source.getIngredientCommands().size() > 0 ){
            source.getIngredientCommands().forEach(ingredientCommand -> recipe.addIngredient(ingredientCommandToIngredientConverter.convert(ingredientCommand)));
        }


        return recipe;
    }
}
