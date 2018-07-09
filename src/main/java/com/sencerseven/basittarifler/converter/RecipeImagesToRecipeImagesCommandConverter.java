package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.RecipeImagesCommand;
import com.sencerseven.basittarifler.domain.RecipeImages;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeImagesToRecipeImagesCommandConverter implements Converter<RecipeImages,RecipeImagesCommand> {


    @Override
    public RecipeImagesCommand convert(RecipeImages recipeImages) {
        if (recipeImages == null)
            return null;

        RecipeImagesCommand recipeImagesCommand = new RecipeImagesCommand();

        recipeImagesCommand.setId(recipeImages.getId());
        recipeImagesCommand.setDescription(recipeImages.getDescription());
        recipeImagesCommand.setImageUrl(recipeImages.getUrl());


        return recipeImagesCommand;
    }
}
