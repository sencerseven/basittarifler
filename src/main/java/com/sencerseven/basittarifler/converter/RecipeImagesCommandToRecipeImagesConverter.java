package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.RecipeImagesCommand;
import com.sencerseven.basittarifler.domain.RecipeImages;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeImagesCommandToRecipeImagesConverter implements Converter<RecipeImagesCommand,RecipeImages> {


    @Override
    public RecipeImages convert(RecipeImagesCommand recipeImagesCommand) {
        if(recipeImagesCommand == null)
            return null;

        RecipeImages recipeImages = new RecipeImages();
        recipeImages.setId(recipeImagesCommand.getId());
        recipeImages.setDescription(recipeImagesCommand.getDescription());
        recipeImages.setUrl(recipeImagesCommand.getUrl());

        return recipeImages;
    }
}
