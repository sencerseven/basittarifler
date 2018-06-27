package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.NutritionCommand;
import com.sencerseven.basittarifler.domain.Nutrition;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NutritionToNutritionCommandConverter implements Converter<Nutrition,NutritionCommand> {

    @Override
    public NutritionCommand convert(Nutrition nutrition) {
        if(nutrition == null)
            return null;

        NutritionCommand nutritionCommand = new NutritionCommand();

        nutritionCommand.setId(nutrition.getId());
        nutritionCommand.setCarbonhydrate(nutrition.getCarbonhydrate());
        nutritionCommand.setCholesterol(nutrition.getCholesterol());
        nutritionCommand.setEnergy(nutrition.getEnergy());
        nutritionCommand.setFat(nutrition.getFat());
        nutritionCommand.setFiber(nutrition.getFiber());
        nutritionCommand.setProtine(nutrition.getProtine());
        nutritionCommand.setSaturatedFat(nutrition.getSaturatedFat());
        nutritionCommand.setSugar(nutrition.getSugar());

        return  nutritionCommand;
    }
}
