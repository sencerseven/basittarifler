package com.sencerseven.basittarifler.service;

import com.sencerseven.basittarifler.domain.Category;
import com.sencerseven.basittarifler.repository.CategoryRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Override
    public Set<Category> getCategoriesByMenuActive(int page, int size, boolean status) {
        Set<Category> categories = new HashSet<>();
        categoryRepository.findCategoriesByMenuActive(PageRequest.of(page,size),status).iterator().forEachRemaining(categories::add);
        return categories;
    }


}
