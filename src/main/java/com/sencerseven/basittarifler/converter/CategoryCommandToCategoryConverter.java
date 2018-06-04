package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.CategoryCommand;
import com.sencerseven.basittarifler.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandToCategoryConverter implements Converter<CategoryCommand,Category> {

    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryCommand categoryCommand) {
        if(categoryCommand == null)
            return  null;

        Category category = new Category();
        category.setId(categoryCommand.getId());
        category.setCategoryDescription(categoryCommand.getCategoryDescription());
        category.setCategoryName(categoryCommand.getCategoryName());

        return category;
    }
}
