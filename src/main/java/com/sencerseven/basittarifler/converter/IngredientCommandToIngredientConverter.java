package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.IngredientCommand;
import com.sencerseven.basittarifler.domain.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredientConverter implements Converter<IngredientCommand,Ingredient> {


    IngredientDetailsCommandToIngredienDetailsConverter ingredientDetailsCommandToIngredienDetailsConverter;

    public IngredientCommandToIngredientConverter(IngredientDetailsCommandToIngredienDetailsConverter ingredientDetailsCommandToIngredienDetailsConverter) {
        this.ingredientDetailsCommandToIngredienDetailsConverter = ingredientDetailsCommandToIngredienDetailsConverter;
    }

    @Override
    public Ingredient convert(IngredientCommand ingredientCommand) {
        if(ingredientCommand == null)
            return null;

        Ingredient ingredient = new Ingredient();
        ingredient.setId(ingredientCommand.getId());
        ingredient.setDescription(ingredientCommand.getDescription());

        if(ingredientCommand.getIngredientDetailsCommands() != null && ingredientCommand.getIngredientDetailsCommands().size() > 0)
            ingredientCommand.getIngredientDetailsCommands()
                    .stream()
                    .filter(ingredientDetailsCommand -> (ingredientDetailsCommand.getDescription() != null) )
                    .forEach(ingredientDetailsCommand -> ingredient.addIngredientDetails(ingredientDetailsCommandToIngredienDetailsConverter.convert(ingredientDetailsCommand)));

        return ingredient;


    }
}
