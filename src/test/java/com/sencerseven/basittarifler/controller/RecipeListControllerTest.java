package com.sencerseven.basittarifler.controller;

import com.sencerseven.basittarifler.domain.Recipe;
import com.sencerseven.basittarifler.service.CategoryService;
import com.sencerseven.basittarifler.service.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class RecipeListControllerTest {


    @Mock
    RecipeService recipeService;

    @Mock
    CategoryService categoryService;

    RecipeListController recipeListController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeListController = new RecipeListController(recipeService,categoryService);
    }

    @Test
    public void testMockMVC() throws Exception{
        MockMvc mock = MockMvcBuilders.standaloneSetup(recipeListController).build();


        mock.perform(get("/recipelist").param("test","tst"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void indexAction() {


    }
}