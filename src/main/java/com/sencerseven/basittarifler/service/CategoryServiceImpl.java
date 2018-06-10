package com.sencerseven.basittarifler.service;

import com.sencerseven.basittarifler.domain.Category;
import com.sencerseven.basittarifler.exceptions.NotFoundException;
import com.sencerseven.basittarifler.repository.CategoryRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



import java.util.HashSet;
import java.util.Optional;
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

    @Override
    public Set<Category> defineCategoryParentOrSubsForPage(Long id, String catUrl) {
        Optional<Category> categoryOptional=categoryRepository.findCategoriesByIdAndCategoryUrl(id,catUrl);

        if(!categoryOptional.isPresent())
            throw new NotFoundException();

        Set<Category> categories = new HashSet<>();

        categoryOptional.get().getChildrenCategory().stream().iterator().forEachRemaining(categories::add);

        if(categories.size() != 0)
            return categories;


        categories.add(categoryOptional.get());
        return categories;



    }


}
