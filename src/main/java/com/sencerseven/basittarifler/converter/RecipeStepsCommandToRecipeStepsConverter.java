package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.RecipeStepsCommand;
import com.sencerseven.basittarifler.domain.RecipeSteps;
import com.sencerseven.basittarifler.functions.BasitTariflerHelpers;
import com.sencerseven.basittarifler.functions.BasitTariflerHelpersImpl;
import com.sencerseven.basittarifler.service.S3Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeStepsCommandToRecipeStepsConverter implements Converter<RecipeStepsCommand, RecipeSteps> {

    S3Services s3Services;

    BasitTariflerHelpers basitTariflerHelpers;

    public RecipeStepsCommandToRecipeStepsConverter(S3Services s3Services) {
        this.s3Services = s3Services;
        basitTariflerHelpers = new BasitTariflerHelpersImpl();
    }

    @Override
    public RecipeSteps convert(RecipeStepsCommand recipeStepsCommand) {
        if (recipeStepsCommand == null)
            return null;
        RecipeSteps recipeSteps = new RecipeSteps();
        recipeSteps.setId(recipeStepsCommand.getId());
        recipeSteps.setDescription(recipeStepsCommand.getDescription());

        if(recipeStepsCommand.getImgURL() != null )
            recipeSteps.setImageURL(recipeStepsCommand.getImgURL());

        if (recipeStepsCommand.getImageFile() != null && recipeStepsCommand.getImageFile().getSize() > 0){

            recipeSteps.setImageURL(s3Services.uploadFile(recipeStepsCommand.getImageFile().getOriginalFilename(),
                    "recipe",
                    basitTariflerHelpers.addLogo(recipeStepsCommand.getImageFile())));
        }

        recipeSteps.setViewRows(recipeStepsCommand.getViewRows());

        return recipeSteps;
    }
}
