package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.CategoryCommand;
import com.sencerseven.basittarifler.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryCommandConverter implements Converter<Category, CategoryCommand> {

    @Synchronized
    @Nullable
    @Override
    public CategoryCommand convert(Category category) {
        if (category == null)
            return null;
        final CategoryCommand categoryCommand = new CategoryCommand();

        categoryCommand.setId(category.getId());
        categoryCommand.setCategoryDescription(category.getCategoryDescription());
        categoryCommand.setCategoryName(category.getCategoryName());
        categoryCommand.setMenuActive(category.isMenuActive());
        categoryCommand.setMainPageStatus(category.isMainPageStatus());
        categoryCommand.setCategoryUrl(category.getCategoryUrl());

        if (category.getParentCategory() != null && category.getParentCategory().getId() != null) {
            categoryCommand.setParentCategory(this.convert(category.getParentCategory()));
        }

        return categoryCommand;
    }
}
