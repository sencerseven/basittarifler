package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.IngredientDetailsCommand;
import com.sencerseven.basittarifler.domain.IngredientDetails;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientDetailsToIngredientDetailsCommandConverter implements Converter<IngredientDetails, IngredientDetailsCommand> {


    @Override
    public IngredientDetailsCommand convert(IngredientDetails ingredientDetails) {
        if (ingredientDetails == null)
            return null;

        IngredientDetailsCommand ingredientDetailsCommand = new IngredientDetailsCommand();

        ingredientDetailsCommand.setId(ingredientDetails.getId());
        ingredientDetailsCommand.setDescription(ingredientDetails.getDescription());

        return ingredientDetailsCommand;
    }
}
