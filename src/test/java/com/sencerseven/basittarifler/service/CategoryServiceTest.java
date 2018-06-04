package com.sencerseven.basittarifler.service;

import com.sencerseven.basittarifler.domain.Category;
import com.sencerseven.basittarifler.repository.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CategoryServiceTest {

    @Mock
    CategoryRepository categoryRepository;

    CategoryServiceImpl categoryService;

    @Before
    public void setUp() throws Exception {
        categoryService = new CategoryServiceImpl(categoryRepository);
    }
/*
    @Test
    public void getCategories() throws Exception{
        Category category = new Category();
        category.setId(1L);
        HashSet categories = new HashSet();
        categories.add(category);

        when(categoryService.getCategories()).thenReturn(categories);

        Set<Category> categorySet = categoryService.getCategories();

        assertEquals(categorySet.size(),1);
        verify(categoryRepository,times(1)).findAll();


    }
*/
    @Test
    public void findById() {
    }
}