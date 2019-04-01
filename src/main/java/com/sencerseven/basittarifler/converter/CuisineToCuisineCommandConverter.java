package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.CuisineCommand;
import com.sencerseven.basittarifler.domain.Cuisine;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CuisineToCuisineCommandConverter implements Converter<Cuisine,CuisineCommand> {


    @Override
    public CuisineCommand convert(Cuisine cuisine) {
      if(cuisine == null)
          return null;

      CuisineCommand cuisineCommand = new CuisineCommand();
      cuisineCommand.setId(cuisine.getId());
      cuisineCommand.setCuisine(cuisine.getCuisine());

      return cuisineCommand;
    }
}
