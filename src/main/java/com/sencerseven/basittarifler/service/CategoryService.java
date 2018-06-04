package com.sencerseven.basittarifler.service;

import com.sencerseven.basittarifler.domain.Category;

import java.util.Optional;
import java.util.Set;

public interface CategoryService {

    Set<Category> getCategories();
}
