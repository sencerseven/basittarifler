package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.CuisineCommand;
import com.sencerseven.basittarifler.domain.Cuisine;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CuisineCommandToCuisineConverter implements Converter<CuisineCommand,Cuisine> {


    @Synchronized
    @Nullable
    @Override
    public Cuisine convert(CuisineCommand cuisineCommand) {
        if(cuisineCommand == null)
            return null;

        Cuisine cuisine = new Cuisine();

        cuisine.setId(cuisineCommand.getId());
        cuisine.setCuisine(cuisineCommand.getCuisine());

        return cuisine;

    }
}
