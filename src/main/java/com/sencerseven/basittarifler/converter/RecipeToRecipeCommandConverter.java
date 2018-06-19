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

    public RecipeToRecipeCommandConverter(CategoryToCategoryCommandConverter categoryToCategoryCommandConverter) {
        this.categoryToCategoryCommandConverter = categoryToCategoryCommandConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {
        if(source == null)
            return null;

       final RecipeCommand recipeCommand = new RecipeCommand();

        recipeCommand.setId(source.getId());
        recipeCommand.setRecipeTitle(source.getRecipeTitle());
        recipeCommand.setRecipeDescription(source.getRecipeDescription());
        recipeCommand.setCreated_at(source.getCreatedAt());
        recipeCommand.setRecipeText(source.getRecipeText());

        if(source.getCategories() != null && source.getCategories().size() > 0)
            source.getCategories().forEach((Category category) -> recipeCommand.getCategories().add(categoryToCategoryCommandConverter.convert(category)));

        return  recipeCommand;

    }
}
