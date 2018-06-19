package com.sencerseven.basittarifler.service;

import com.sencerseven.basittarifler.command.CategoryCommand;
import com.sencerseven.basittarifler.converter.CategoryCommandToCategoryConverter;
import com.sencerseven.basittarifler.domain.Category;
import com.sencerseven.basittarifler.exceptions.NotFoundException;
import com.sencerseven.basittarifler.repository.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CategoryServiceTest {

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    CategoryCommandToCategoryConverter categoryCommandToCategoryConverter;

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

    @Test
    public void defineCategoryParentOrSubsForPage(){
        Set<Category> categories = new HashSet<>();
        Category category = new Category();
        category.setId(1L);

        categories.add(category);

        Optional<Category> optionalCategory = Optional.ofNullable(category);


        /** Child Test**/

        Category child = new Category();
        child.setId(2L);

        Category categoryParent = new Category();
        categoryParent.setId(4L);
        categoryParent.getChildrenCategory().add(child);

        Optional<Category> optionalParent = Optional.of(categoryParent);




        when(categoryRepository.findCategoriesByIdAndCategoryUrl(anyLong(),anyString())).thenReturn(Optional.ofNullable(null));

        try{
            Set<Category> categoriesSet = categoryService.defineCategoryParentOrSubsForPage(anyLong(),anyString());

        }catch (Exception e){
            assertEquals(e.getClass(),NotFoundException.class);
        }

        when(categoryRepository.findCategoriesByIdAndCategoryUrl(1L,"oglen-yemegi")).thenReturn(optionalCategory);



        when(categoryRepository.findCategoriesByIdAndCategoryUrl(2L,"aksam-yemegi")).thenReturn(optionalParent);


        Set<Category> categoriesSet = categoryService.defineCategoryParentOrSubsForPage(1L,"oglen-yemegi");

        Set<Category> categoriesSet2 = categoryService.defineCategoryParentOrSubsForPage(2L,"aksam-yemegi");


        assertTrue(categoriesSet.contains(category));
        assertTrue(categoriesSet2.contains(child));

    }

    @Test
    public void saveCategoryCommand(){
        Category category = new Category();
        category.setId(ID);


        when(categoryCommandToCategoryConverter.convert(any())).thenReturn(category);
        when(categoryRepository.save(any())).thenReturn(category);
        Category returnCategory = categoryService.saveCategoryCommand(new CategoryCommand());

        assertEquals(ID,returnCategory.getId());
    }
}