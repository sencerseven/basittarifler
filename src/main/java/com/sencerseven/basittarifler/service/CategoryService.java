package com.sencerseven.basittarifler.service;

import com.sencerseven.basittarifler.domain.Category;

import java.util.Set;

public interface CategoryService {

    Set<Category> getCategories();

    Set<Category> getCategoriesByMenuActive(int page,int size, boolean status);

    Set<Category> defineCategoryParentOrSubsForPage(Long id,String catUrl);
}
