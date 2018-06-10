package com.sencerseven.basittarifler.service;

import com.sencerseven.basittarifler.domain.Category;
import com.sencerseven.basittarifler.repository.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CategoryServiceTest {

    @Mock
    CategoryRepository categoryRepository;

    CategoryServiceImpl categoryService;

    private static final Long ID =1L;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

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

    @Test
    public void getCategoriesByMenuActive(){
        Category category = new Category();
        category.setId(ID);
        List<Category> categories = new ArrayList<>();
        categories.add(category);

        when(categoryRepository.findCategoriesByMenuActive(any(PageRequest.class),any(Boolean.class))).thenReturn(categories);

        Set<Category> resultCategory = categoryService.getCategoriesByMenuActive(0,1,true);

        assertEquals(resultCategory.size(),1);
        verify(categoryRepository,times(1)).findCategoriesByMenuActive(any(PageRequest.class),any(Boolean.class));

    }
}