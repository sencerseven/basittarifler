package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.RecipeCommand;
import com.sencerseven.basittarifler.domain.Category;
import com.sencerseven.basittarifler.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipeConverter implements Converter<RecipeCommand,Recipe> {


    CategoryCommandToCategoryConverter categoryCommandToCategoryConverter;

    public RecipeCommandToRecipeConverter(CategoryCommandToCategoryConverter categoryCommandToCategoryConverter) {
        this.categoryCommandToCategoryConverter = categoryCommandToCategoryConverter;
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
        recipe.setCreated_at(source.getCreated_at());

        if(source.getCategories() != null && source.getCategories().size() > 0 )
            source.getCategories().forEach(category -> recipe.getCategories().add(categoryCommandToCategoryConverter.convert(category)));


        return recipe;
    }
}
