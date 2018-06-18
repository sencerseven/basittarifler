package com.sencerseven.basittarifler.service;

import com.sencerseven.basittarifler.command.CategoryCommand;
import com.sencerseven.basittarifler.domain.Category;

import java.util.List;
import java.util.Set;

public interface CategoryService {

    Category getById(Long id);

    Set<Category> getCategories();

    Set<Category> getCategoriesByMenuActive(int page,int size, boolean status);

    Set<Category> defineCategoryParentOrSubsForPage(Long id,String catUrl);

    Category saveCategoryCommand(CategoryCommand categoryCommand);

    void deleteCategory(Long id);
}
