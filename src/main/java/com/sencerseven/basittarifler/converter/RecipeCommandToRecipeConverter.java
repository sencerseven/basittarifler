package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.RecipeCommand;
import com.sencerseven.basittarifler.domain.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipeConverter implements Converter<RecipeCommand,Recipe> {


    CategoryCommandToCategoryConverter categoryCommandToCategoryConverter;
    RecipeStepsCommandToRecipeStepsConverter recipeStepsCommandToRecipeStepsConverter;

    public RecipeCommandToRecipeConverter(CategoryCommandToCategoryConverter categoryCommandToCategoryConverter, RecipeStepsCommandToRecipeStepsConverter recipeStepsCommandToRecipeStepsConverter) {
        this.categoryCommandToCategoryConverter = categoryCommandToCategoryConverter;
        this.recipeStepsCommandToRecipeStepsConverter = recipeStepsCommandToRecipeStepsConverter;
    }

    @Override
    public Recipe convert(RecipeCommand source) {
        if(source == null)
            return null;

        Recipe recipe = new Recipe();

        recipe.setId(source.getId());
        recipe.setRecipeDescription(source.getRecipeDescription());
        recipe.setRecipeText(source.getRecipeText());
        recipe.setRecipeTitle(source.getRecipeTitle());
        recipe.setPerson(source.getPerson());
        recipe.setPortion(source.getPortion());
        recipe.setCookMin(source.getCookMin());
        recipe.setPrepMin(source.getPrepMin());
        recipe.setCreatedAt(source.getCreated_at());

        if(source.getCategories() != null && source.getCategories().size() > 0 )
            source.getCategories().forEach(category -> recipe.getCategories().add(categoryCommandToCategoryConverter.convert(category)));

        if(source.getRecipeSteps() != null && source.getRecipeSteps().size() > 0)
            source.getRecipeSteps().forEach(recipeSteps -> recipe.addRecipeSteps(recipeStepsCommandToRecipeStepsConverter.convert(recipeSteps)));

        return recipe;
    }
}
