package com.sencerseven.basittarifler.service;

import com.sencerseven.basittarifler.converter.CategoryToCategoryCommandConverter;
import com.sencerseven.basittarifler.converter.RecipeCommandToRecipeConverter;
import com.sencerseven.basittarifler.converter.RecipeToRecipeCommandConverter;
import com.sencerseven.basittarifler.domain.Recipe;
import com.sencerseven.basittarifler.domain.Users;
import com.sencerseven.basittarifler.repository.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.swing.text.html.Option;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;

    RecipeServiceImpl recipeService;

    @Mock
    CategoryToCategoryCommandConverter categoryToCategoryCommandConverter;

    RecipeToRecipeCommandConverter recipeToRecipeCommandConverter;

    public static final Long ID =1L;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeToRecipeCommandConverter = new RecipeToRecipeCommandConverter(categoryToCategoryCommandConverter);
        recipeService = new RecipeServiceImpl(recipeRepository,recipeToRecipeCommandConverter);
    }

    @Test
    public void getAllRecipes() {
        Recipe recipe = new Recipe();
        List recipes = new ArrayList();
        recipes.add(recipe);


        when(recipeService.getAllRecipes()).thenReturn(recipes);
        List<Recipe> recipeSet = recipeService.getAllRecipes();

        assertEquals(recipeSet.size(), 1);
        verify(recipeRepository,times(1)).findAll();

    }

    @Test
    public void getLimitRecipes() {
        Recipe recipe = new Recipe();
        List recipes = new ArrayList();
        recipes.add(recipe);

        when(recipeService.getLimitRecipes(PageRequest.of(0,1))).thenReturn(recipes);

        List<Recipe> recipeSet =recipeService.getLimitRecipes(PageRequest.of(0,1));


        verify(recipeRepository, times(1)).findRecipesBy(PageRequest.of(0,1));

    }

    @Test
    public void getFindById(){
        Recipe recipe = new Recipe();
        recipe.setId(ID);

        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(ID)).thenReturn(recipeOptional);

        Recipe recipeResult = recipeService.getFindById(ID);

        verify(recipeRepository,times(1)).findById(anyLong());
        assertEquals(ID,recipeResult.getId());

    }

    @Test
    public void getRecipesByUsers(){
        Users users = new Users();
        users.setId(ID);

        Recipe recipe = new Recipe();
        recipe.setId(ID);
        List<Recipe> recipeList = new ArrayList<>();
        recipeList.add(recipe);

        when(recipeRepository.findRecipesByUsers(any(PageRequest.class),any(Users.class))).thenReturn(recipeList);

        List<Recipe> recipeListResponse = recipeService.getRecipesByUsers(0,3,users);

        verify(recipeRepository,times(1)).findRecipesByUsers(any(),any());
        assertEquals(1,recipeListResponse.size());


    }


}