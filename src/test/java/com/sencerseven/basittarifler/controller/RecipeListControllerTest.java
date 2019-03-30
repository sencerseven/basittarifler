package com.sencerseven.basittarifler.controller;

import com.sencerseven.basittarifler.domain.Category;
import com.sencerseven.basittarifler.domain.Recipe;
import com.sencerseven.basittarifler.service.CategoryService;
import com.sencerseven.basittarifler.service.PostsService;
import com.sencerseven.basittarifler.service.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.*;
import java.util.function.Function;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class RecipeListControllerTest {


    @Mock
    RecipeService recipeService;

    @Mock
    CategoryService categoryService;

    @Mock
    PostsService postsService;

    RecipeListController recipeListController;

    @Mock
    Model model;

    private static final Long ID = 1L;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeListController = new RecipeListController(recipeService,categoryService,postsService);
    }

    @Test
    public void testMockMVC() throws Exception{
        MockMvc mock = MockMvcBuilders.standaloneSetup(recipeListController).build();


        mock.perform(get("/recipelist").param("test","tst"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));

        mock.perform(get("/recipelist/1/url"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void indexAction() {

        List<Recipe> recipeList = new ArrayList<>();
        Recipe recipe = new Recipe();
        recipe.setId(ID);
        recipeList.add(recipe);


        Set<Category> categorySet = new HashSet<>();
        Category category = new Category();
        categorySet.add(category);


        when(recipeService.findAllByOrderByCreatedAtDesc(anyInt(),anyInt())).thenReturn(new Page<Recipe>() {
            @Override
            public int getTotalPages() {
                return 1;
            }

            @Override
            public long getTotalElements() {
                return 1;
            }

            @Override
            public <U> Page<U> map(Function<? super Recipe, ? extends U> function) {
                return null;
            }

            @Override
            public int getNumber() {
                return 1;
            }

            @Override
            public int getSize() {
                return 1;
            }

            @Override
            public int getNumberOfElements() {
                return 1;
            }

            @Override
            public List<Recipe> getContent() {
                return null;
            }

            @Override
            public boolean hasContent() {
                return false;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public boolean isFirst() {
                return false;
            }

            @Override
            public boolean isLast() {
                return false;
            }

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public Pageable nextPageable() {
                return null;
            }

            @Override
            public Pageable previousPageable() {
                return null;
            }

            @Override
            public Iterator<Recipe> iterator() {
                return null;
            }
        });




        when(recipeService.getAllPopulerRecipe(any(Integer.class),any(Integer.class))).thenReturn(recipeList);
        when(categoryService.getCategoriesByMenuActive(any(Integer.class),any(Integer.class),any(Boolean.class))).thenReturn(categorySet);

        ArgumentCaptor<Page<Recipe>> pageRecipe = ArgumentCaptor.forClass(Page.class);
        ArgumentCaptor<List<Recipe>> recipeListCaptor = ArgumentCaptor.forClass(List.class);
        ArgumentCaptor<Set<Category>> categorySetCaptor = ArgumentCaptor.forClass(HashSet.class);


        String view = null;
        try {
            view = recipeListController.indexAction(model,1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals("index",view);


        verify(recipeService,times(1)).findAllByOrderByCreatedAtDesc(any(Integer.class),any(Integer.class));
        verify(model,times(1)).addAttribute(eq("recipePage"),pageRecipe.capture());

        verify(recipeService,times(1)).getAllPopulerRecipe(any(Integer.class),any(Integer.class));
        verify(model,times(1)).addAttribute(eq("recipePopular"),recipeListCaptor.capture());

        verify(categoryService,times(1)).getCategoriesByMenuActive(any(Integer.class),any(Integer.class),any(Boolean.class));
        verify(model,times(1)).addAttribute(eq("categories"),categorySetCaptor.capture());


        Page<Recipe> recipePageValue = pageRecipe.getValue();
        List<Recipe> listRecipeValue = recipeListCaptor.getValue();
        Set<Category> setCategoryValue = categorySetCaptor.getValue();


        assertEquals(1,recipePageValue.getSize());
        assertEquals(1,listRecipeValue.size());
        assertEquals(1,setCategoryValue.size());

    }
}