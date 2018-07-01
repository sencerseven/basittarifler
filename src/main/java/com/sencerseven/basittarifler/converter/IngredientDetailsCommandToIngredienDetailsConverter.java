package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.IngredientDetailsCommand;
import com.sencerseven.basittarifler.domain.IngredientDetails;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientDetailsCommandToIngredienDetailsConverter implements Converter<IngredientDetailsCommand,IngredientDetails> {


    @Override
    public IngredientDetails convert(IngredientDetailsCommand ingredientDetailsCommand) {
        if(ingredientDetailsCommand == null)
            return null;

        IngredientDetails ingredientDetails = new IngredientDetails();

        ingredientDetails.setId(ingredientDetailsCommand.getId());
        ingredientDetails.setDescription(ingredientDetailsCommand.getDescription());

        return  ingredientDetails;

    }
}
