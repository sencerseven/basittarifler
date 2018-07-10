package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.IngredientCommand;
import com.sencerseven.basittarifler.domain.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommandConverter implements Converter<Ingredient,IngredientCommand> {


    IngredientDetailsToIngredientDetailsCommandConverter ingredientDetailsToIngredientDetailsCommandConverter;

    public IngredientToIngredientCommandConverter(IngredientDetailsToIngredientDetailsCommandConverter ingredientDetailsToIngredientDetailsCommandConverter) {
        this.ingredientDetailsToIngredientDetailsCommandConverter = ingredientDetailsToIngredientDetailsCommandConverter;
    }

    @Override
    public IngredientCommand convert(Ingredient ingredient) {
        if(ingredient == null)
            return  null;
        IngredientCommand ingredientCommand = new IngredientCommand();

        ingredientCommand.setId(ingredient.getId());
        ingredientCommand.setDescription(ingredient.getDescription());


        if (ingredient.getIngredientDetails() != null && ingredient.getIngredientDetails().size()>0){
            ingredient.getIngredientDetails().forEach(ingredientDetails -> ingredientCommand.getIngredientDetailsCommands().add(ingredientDetailsToIngredientDetailsCommandConverter.convert(ingredientDetails)));
        }

        return ingredientCommand;


    }
}
