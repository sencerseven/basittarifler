package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.RecipeStepsCommand;
import com.sencerseven.basittarifler.domain.RecipeSteps;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeStepsToRecipeStepsCommandConverter implements Converter<RecipeSteps,RecipeStepsCommand> {

    @Override
    public RecipeStepsCommand convert(RecipeSteps recipeSteps) {
        if(recipeSteps == null)
            return null;
        RecipeStepsCommand recipeStepsCommand = new RecipeStepsCommand();
        recipeStepsCommand.setId(recipeSteps.getId());
        recipeStepsCommand.setDescription(recipeSteps.getDescription());

        recipeStepsCommand.setViewRows(recipeSteps.getViewRows());

        return recipeStepsCommand;
    }
}
