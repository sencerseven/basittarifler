package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.RecipeTipsCommand;
import com.sencerseven.basittarifler.domain.RecipeTips;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeTipsCommandToRecipeTipsConverter implements Converter<RecipeTipsCommand,RecipeTips> {


    @Synchronized
    @Nullable
    @Override
    public RecipeTips convert(RecipeTipsCommand recipeTipsCommand) {
        if(recipeTipsCommand == null)
            return null;

        System.out.println(recipeTipsCommand.getDescription());

        RecipeTips recipeTips = new RecipeTips();
        recipeTips.setId(recipeTipsCommand.getId());
        recipeTips.setDescription(recipeTipsCommand.getDescription());
        recipeTips.setViewRows(recipeTipsCommand.getViewRows());

        return recipeTips;
    }
}
