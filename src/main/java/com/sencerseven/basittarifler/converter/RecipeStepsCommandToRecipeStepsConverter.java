package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.RecipeStepsCommand;
import com.sencerseven.basittarifler.domain.RecipeSteps;
import com.sencerseven.basittarifler.service.S3Services;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeStepsCommandToRecipeStepsConverter implements Converter<RecipeStepsCommand, RecipeSteps> {

    S3Services s3Services;

    public RecipeStepsCommandToRecipeStepsConverter(S3Services s3Services) {
        this.s3Services = s3Services;
    }

    @Override
    public RecipeSteps convert(RecipeStepsCommand recipeStepsCommand) {
        if (recipeStepsCommand == null)
            return null;
        RecipeSteps recipeSteps = new RecipeSteps();
        recipeSteps.setId(recipeStepsCommand.getId());
        recipeSteps.setDescription(recipeStepsCommand.getDescription());

        if (recipeStepsCommand.getImageFile() != null && recipeStepsCommand.getImageFile().getSize() > 0)
            recipeSteps.setImageURL(s3Services.uploadFile(recipeStepsCommand.getImageFile().getOriginalFilename(), "recipe", recipeStepsCommand.getImageFile()));

        recipeSteps.setViewRows(recipeStepsCommand.getViewRows());

        return recipeSteps;
    }
}
