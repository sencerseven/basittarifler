package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.RecipeImagesCommand;
import com.sencerseven.basittarifler.domain.RecipeImages;
import com.sencerseven.basittarifler.service.S3Services;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeImagesCommandToRecipeImagesConverter implements Converter<RecipeImagesCommand,RecipeImages> {

    S3Services s3Services;

    public RecipeImagesCommandToRecipeImagesConverter(S3Services s3Services) {
        this.s3Services = s3Services;
    }

    @Override
    public RecipeImages convert(RecipeImagesCommand recipeImagesCommand) {
        if(recipeImagesCommand == null)
            return null;

        RecipeImages recipeImages = new RecipeImages();
        recipeImages.setId(recipeImagesCommand.getId());
        recipeImages.setDescription(recipeImagesCommand.getDescription());

        if(recipeImagesCommand.getImageUrl() != null)
        recipeImages.setUrl(recipeImagesCommand.getImageUrl());

        if(recipeImagesCommand.getImageFile() != null && recipeImagesCommand.getImageFile().getSize() > 0)
        recipeImages.setUrl(s3Services.uploadFile(recipeImagesCommand.getImageFile().getOriginalFilename(), "recipe", recipeImagesCommand.getImageFile()));

        return recipeImages;
    }
}
