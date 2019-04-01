package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.RecipeCommand;
import com.sencerseven.basittarifler.domain.Category;
import com.sencerseven.basittarifler.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommandConverter implements Converter<Recipe,RecipeCommand> {

    CategoryToCategoryCommandConverter categoryToCategoryCommandConverter;

    RecipeStepsToRecipeStepsCommandConverter recipeStepsToRecipeStepsCommandConverter;
    RecipeImagesToRecipeImagesCommandConverter recipeImagesToRecipeImagesCommandConverter;
    NutritionToNutritionCommandConverter nutritionToNutritionCommandConverter;
    IngredientToIngredientCommandConverter ingredientToIngredientCommandConverter;
    RecipeTipsToRecipeTipsCommmandConverter recipeTipsToRecipeTipsCommmandConverter;
    TagsToTagsCommandConverter tagsToTagsCommandConverter;

    public RecipeToRecipeCommandConverter(CategoryToCategoryCommandConverter categoryToCategoryCommandConverter, RecipeStepsToRecipeStepsCommandConverter recipeStepsToRecipeStepsCommandConverter, RecipeImagesToRecipeImagesCommandConverter recipeImagesToRecipeImagesCommandConverter, NutritionToNutritionCommandConverter nutritionToNutritionCommandConverter, IngredientToIngredientCommandConverter ingredientToIngredientCommandConverter, RecipeTipsToRecipeTipsCommmandConverter recipeTipsToRecipeTipsCommmandConverter, TagsToTagsCommandConverter tagsToTagsCommandConverter) {
        this.categoryToCategoryCommandConverter = categoryToCategoryCommandConverter;
        this.recipeStepsToRecipeStepsCommandConverter = recipeStepsToRecipeStepsCommandConverter;
        this.recipeImagesToRecipeImagesCommandConverter = recipeImagesToRecipeImagesCommandConverter;
        this.nutritionToNutritionCommandConverter = nutritionToNutritionCommandConverter;
        this.ingredientToIngredientCommandConverter = ingredientToIngredientCommandConverter;
        this.recipeTipsToRecipeTipsCommmandConverter = recipeTipsToRecipeTipsCommmandConverter;
        this.tagsToTagsCommandConverter = tagsToTagsCommandConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {
        if(source == null)
            return null;

       final RecipeCommand recipeCommand = new RecipeCommand();

        recipeCommand.setId(source.getId());
        recipeCommand.setRecipeTitle(source.getTitle());
        recipeCommand.setRecipeDescription(source.getDescription());
        recipeCommand.setCreated_at(source.getCreatedAt());
        recipeCommand.setRecipeText(source.getRecipeText());

        recipeCommand.setPerson(source.getPerson());
        recipeCommand.setPortion(source.getPortion());
        recipeCommand.setPrepMin(source.getPrepMin());
        recipeCommand.setCookMin(source.getCookMin());


        if(source.getCategories() != null && source.getCategories().size() > 0)
            source.getCategories().forEach((Category category) -> recipeCommand.getCategories().add(categoryToCategoryCommandConverter.convert(category)));

        if(source.getRecipeSteps() != null && source.getRecipeSteps().size() > 0)
            source.getRecipeSteps().forEach(recipeSteps -> recipeCommand.getRecipeSteps().add(recipeStepsToRecipeStepsCommandConverter.convert(recipeSteps)));

        if(source.getRecipeImages() != null && source.getRecipeImages().size() > 0){
            source.getRecipeImages().forEach(recipeImages -> recipeCommand.getRecipeImagesCommands().add(recipeImagesToRecipeImagesCommandConverter.convert(recipeImages)));
        }

        if(source.getIngredients() != null && source.getIngredients().size()>0){
            source.getIngredients().forEach(ingredient -> recipeCommand.getIngredientCommands().add(ingredientToIngredientCommandConverter.convert(ingredient)));
        }

        if(source.getNutrition() != null)
            recipeCommand.setNutritionCommand(nutritionToNutritionCommandConverter.convert(source.getNutrition()));


        if(source.getRecipeTips() != null && source.getRecipeTips().size()>0)
            source.getRecipeTips().forEach(recipeTips -> recipeCommand.getRecipeTipsCommands().add(recipeTipsToRecipeTipsCommmandConverter.convert(recipeTips)));


        if(source.getTags() != null)
            recipeCommand.setTagsCommands(tagsToTagsCommandConverter.convert(source.getTags()));

        return  recipeCommand;

    }
}
