package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.RecipeCommand;
import com.sencerseven.basittarifler.domain.Recipe;
import com.sencerseven.basittarifler.functions.BasitTariflerHelpers;
import com.sencerseven.basittarifler.functions.BasitTariflerHelpersImpl;
import com.sencerseven.basittarifler.service.CategoryService;
import com.sencerseven.basittarifler.service.CuisineService;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
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
    CuisineCommandToCuisineConverter cuisineCommandToCuisineConverter;
    CuisineService cuisineService;
    TagsCommandToTagsConverter tagsCommandToTagsConverter;

    @Autowired
    BasitTariflerHelpers basitTariflerHelpers;


    public RecipeCommandToRecipeConverter(CategoryCommandToCategoryConverter categoryCommandToCategoryConverter, RecipeStepsCommandToRecipeStepsConverter recipeStepsCommandToRecipeStepsConverter, NutritionCommandToNutritionConverter nutritionCommandToNutritionConverter, RecipeTipsCommandToRecipeTipsConverter recipeTipsCommandToRecipeTipsConverter, RecipeImagesCommandToRecipeImagesConverter recipeImagesCommandToRecipeImagesConverter, IngredientCommandToIngredientConverter ingredientCommandToIngredientConverter, CategoryService categoryService, CuisineCommandToCuisineConverter cuisineCommandToCuisineConverter, CuisineService cuisineService, TagsCommandToTagsConverter tagsCommandToTagsConverter) {
        this.categoryCommandToCategoryConverter = categoryCommandToCategoryConverter;
        this.recipeStepsCommandToRecipeStepsConverter = recipeStepsCommandToRecipeStepsConverter;
        this.nutritionCommandToNutritionConverter = nutritionCommandToNutritionConverter;
        this.recipeTipsCommandToRecipeTipsConverter = recipeTipsCommandToRecipeTipsConverter;
        this.recipeImagesCommandToRecipeImagesConverter = recipeImagesCommandToRecipeImagesConverter;
        this.ingredientCommandToIngredientConverter = ingredientCommandToIngredientConverter;
        this.categoryService = categoryService;
        this.cuisineCommandToCuisineConverter = cuisineCommandToCuisineConverter;
        this.cuisineService = cuisineService;
        this.tagsCommandToTagsConverter = tagsCommandToTagsConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand source) {
        if (source == null)
            return null;

        Recipe recipe = new Recipe();

        recipe.setId(source.getId());
        recipe.setDescription(source.getRecipeDescription());
        recipe.setRecipeText(source.getRecipeText());
        recipe.setTitle(source.getRecipeTitle());
        recipe.setDifficulty(source.getDifficulty());
        recipe.setRecipeUrl(basitTariflerHelpers.toSlug(source.getRecipeTitle()));
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
            source.getRecipeSteps()
                    .stream()
                    .filter(recipeStepsCommand -> (recipeStepsCommand.getImgURL() != null || (recipeStepsCommand.getImageFile() != null && recipeStepsCommand.getImageFile().getSize()>0)))
                    .forEach(recipeSteps -> recipe.addRecipeSteps(recipeStepsCommandToRecipeStepsConverter.convert(recipeSteps)));

        if (source.getRecipeTipsCommands() != null && source.getRecipeTipsCommands().size() > 0)
            source.getRecipeTipsCommands().stream().filter(recipeTipsCommand -> recipeTipsCommand.getDescription() != null).forEach(recipeTipsCommand -> recipe.addRecipeTips(recipeTipsCommandToRecipeTipsConverter.convert(recipeTipsCommand)));

        if(source.getRecipeImagesCommands() != null && source.getRecipeImagesCommands().size()>0){
            source.getRecipeImagesCommands()
                    .stream()
                    .filter(recipeImagesCommand -> (recipeImagesCommand.getImageUrl() != null || (recipeImagesCommand.getImageFile() != null && recipeImagesCommand.getImageFile().getSize()>0)))
                    .forEach(recipeImagesCommand -> recipe.addRecipeImages(recipeImagesCommandToRecipeImagesConverter.convert(recipeImagesCommand)));
        }

        if(source.getIngredientCommands() != null && source.getIngredientCommands().size() > 0 ){
            source.getIngredientCommands().stream()
                    .filter(ingredientCommand -> (ingredientCommand.getDescription() != null && !ingredientCommand.getDescription().equals("")))
                    .forEach(ingredientCommand -> recipe.addIngredient(ingredientCommandToIngredientConverter.convert(ingredientCommand)));
        }

        if(source.getCuisineCommand() != null && source.getCuisineCommand().getId() != null)
            recipe.addCuisine(cuisineService.getById(source.getCuisineCommand().getId()));


        if(source.getTagsCommands() != null){
            recipe.addTags(tagsCommandToTagsConverter.convert(source.getTagsCommands()));
        }

        return recipe;
    }
}
