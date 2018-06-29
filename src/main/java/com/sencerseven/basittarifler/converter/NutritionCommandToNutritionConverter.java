package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.NutritionCommand;
import com.sencerseven.basittarifler.domain.Nutrition;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NutritionCommandToNutritionConverter implements Converter<NutritionCommand,Nutrition> {

    @Override
    public Nutrition convert(NutritionCommand nutritionCommand) {
        if(nutritionCommand == null)
            return null;

        Nutrition nutrition = new Nutrition();
        nutrition.setId(nutritionCommand.getId());
        nutrition.setProtine(nutritionCommand.getProtine());
        nutrition.setFat(nutritionCommand.getFat());
        nutrition.setCarbonhydrate(nutritionCommand.getCarbonhydrate());
        nutrition.setCholesterol(nutritionCommand.getCholesterol());
        nutrition.setEnergy(nutritionCommand.getEnergy());
        nutrition.setFiber(nutritionCommand.getFiber());
        nutrition.setSaturatedFat(nutritionCommand.getSaturatedFat());
        nutrition.setSugar(nutritionCommand.getSugar());

        return nutrition;
    }
}
