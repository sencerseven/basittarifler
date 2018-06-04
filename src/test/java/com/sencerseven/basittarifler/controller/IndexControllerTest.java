package com.sencerseven.basittarifler.controller;

import com.sencerseven.basittarifler.domain.Recipe;
import com.sencerseven.basittarifler.service.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest {

    IndexController controller;

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new IndexController(recipeService);
    }

    @Test
    public void testMockMVC() throws Exception{
        MockMvc mock = MockMvcBuilders.standaloneSetup(controller).build();

        mock.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));

        mock.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void getIndexPage() {
        int page = 0;
        int size = 3;

        List<Recipe> recipeList  = new ArrayList<>();
        recipeList.add(new Recipe());

        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipeList.add(recipe);

        when(recipeService.getLimitRecipes(PageRequest.of(page,size))).thenReturn(recipeList);

        ArgumentCaptor<List<Recipe>> argumentCaptor = ArgumentCaptor.forClass(List.class);

        //when

        String viewName = controller.indexAction(model);

        assertEquals("index",viewName);
        verify(recipeService,times(1)).getLimitRecipes(PageRequest.of(page,size));
        verify(model,times(1)).addAttribute(eq("recipes"),argumentCaptor.capture());
        List<Recipe> captured = argumentCaptor.getValue();
        assertEquals(2,captured.size());


    }
}