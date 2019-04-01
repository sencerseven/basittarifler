package com.sencerseven.basittarifler.service;

import com.sencerseven.basittarifler.command.CategoryCommand;
import com.sencerseven.basittarifler.converter.CategoryCommandToCategoryConverter;
import com.sencerseven.basittarifler.domain.Category;
import com.sencerseven.basittarifler.exceptions.NotFoundException;
import com.sencerseven.basittarifler.repository.CategoryRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;
    CategoryCommandToCategoryConverter categoryCommandToCategoryConverter;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        this.categoryCommandToCategoryConverter = new CategoryCommandToCategoryConverter(this);
    }

    @Override
    public Category getById(Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if(!categoryOptional.isPresent())
            throw new NullPointerException();
        return categoryOptional.get();
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
    @Override
    public Category saveCategoryCommand(CategoryCommand categoryCommand) {
        if(categoryCommand != null){
            Category category = categoryCommandToCategoryConverter.convert(categoryCommand);

            return categoryRepository.save(category);
        }

        return null;
    }
    @Transactional
    @Override
    public void deleteCategory(Long id) {

        Optional<Category> category = categoryRepository.findById(id);

        if(category.isPresent()){
           category.get().getChildrenCategory().forEach(category.get()::removeChildren);
           categoryRepository.deleteCategoryById(id);
        }

    }


}
