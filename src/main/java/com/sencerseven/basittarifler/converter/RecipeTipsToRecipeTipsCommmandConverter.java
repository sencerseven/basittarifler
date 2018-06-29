package com.sencerseven.basittarifler.converter;


import com.sencerseven.basittarifler.command.RecipeTipsCommand;
import com.sencerseven.basittarifler.domain.RecipeTips;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeTipsToRecipeTipsCommmandConverter implements  Converter<RecipeTips,RecipeTipsCommand> {


    @Override
    public RecipeTipsCommand convert(RecipeTips recipeTips) {
        if (recipeTips == null)
            return null;
        RecipeTipsCommand recipeTipsCommand = new RecipeTipsCommand();

        recipeTipsCommand.setId(recipeTips.getId());
        recipeTipsCommand.setDescription(recipeTips.getDescription());
        recipeTipsCommand.setViewRows(recipeTips.getViewRows());

        return recipeTipsCommand;
    }
}
