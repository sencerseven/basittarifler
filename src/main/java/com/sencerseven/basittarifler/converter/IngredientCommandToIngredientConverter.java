package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.IngredientCommand;
import com.sencerseven.basittarifler.domain.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredientConverter implements Converter<IngredientCommand,Ingredient> {


    @Override
    public Ingredient convert(IngredientCommand ingredientCommand) {
        if(ingredientCommand == null)
            return null;

        Ingredient ingredient = new Ingredient();
        ingredient.setId(ingredientCommand.getId());
        ingredient.setDescription(ingredientCommand.getDescription());

        return ingredient;


    }
}
