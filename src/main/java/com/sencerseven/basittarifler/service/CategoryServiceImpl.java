package com.sencerseven.basittarifler.service;

import com.sencerseven.basittarifler.domain.Category;
import com.sencerseven.basittarifler.repository.CategoryRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Set<Category> getCategories() {

        Set<Category> categories = new HashSet();
        categoryRepository.findAll().iterator().forEachRemaining(categories::add);
        return categories;
    }


}
