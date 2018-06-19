package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.CategoryCommand;
import com.sencerseven.basittarifler.domain.Category;
import com.sencerseven.basittarifler.functions.BasitTarifHelpers;
import com.sencerseven.basittarifler.service.CategoryService;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandToCategoryConverter implements Converter<CategoryCommand, Category> {

    CategoryService categoryService;


    public CategoryCommandToCategoryConverter(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryCommand categoryCommand) {
        if (categoryCommand == null)
            return null;

        Category category = new Category();
        category.setId(categoryCommand.getId());
        category.setCategoryDescription(categoryCommand.getCategoryDescription());
        category.setCategoryName(categoryCommand.getCategoryName());
        category.setMenuActive(categoryCommand.isMenuActive());
        category.setCategoryUrl(BasitTarifHelpers.toSlug(categoryCommand.getCategoryName()));


        if (categoryCommand.getParentCategory() != null && categoryCommand.getParentCategory().getId() != null) {
            category.setParentCategory(this.convert(categoryCommand.getParentCategory()));
        }

        return category;
    }
}
