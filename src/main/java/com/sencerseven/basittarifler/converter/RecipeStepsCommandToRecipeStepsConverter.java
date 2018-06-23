package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.RecipeStepsCommand;
import com.sencerseven.basittarifler.domain.RecipeSteps;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeStepsCommandToRecipeStepsConverter implements Converter<RecipeStepsCommand,RecipeSteps> {

    @Override
    public RecipeSteps convert(RecipeStepsCommand recipeStepsCommand) {
        if(recipeStepsCommand == null)
            return null;
        RecipeSteps recipeSteps = new RecipeSteps();
        recipeSteps.setId(recipeStepsCommand.getId());
        recipeSteps.setDescription(recipeStepsCommand.getDescription());
        recipeSteps.setImageURL(recipeStepsCommand.getImageURL());
        recipeSteps.setViewRows(recipeStepsCommand.getViewRows());

        return recipeSteps;
    }
}
