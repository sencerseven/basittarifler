package com.sencerseven.basittarifler.service;

import com.sencerseven.basittarifler.domain.Recipe;
import com.sencerseven.basittarifler.domain.RecipeTips;
import com.sencerseven.basittarifler.repository.RecipeTipsRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RecipeTipsServiceImplTest {

    @Mock
    RecipeTipsRepository recipeTipsRepository;

    RecipeTipsServiceImpl recipeTipsService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeTipsService = new RecipeTipsServiceImpl(recipeTipsRepository);
    }

    @Test
    public void getByRecipeOrderByViewRows() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        RecipeTips recipeTips = new RecipeTips();
        recipeTips.setId(1L);



        List<RecipeTips> recipeTipsList = new ArrayList<>();
        recipeTipsList.add(recipeTips);

        when(recipeTipsService.getByRecipeOrderByViewRows(any())).thenReturn(recipeTipsList);

        List<RecipeTips> recipeListResponse = recipeTipsService.getByRecipeOrderByViewRows(recipe);

        assertEquals(1,recipeListResponse.size());
        verify(recipeTipsRepository,times(1)).findByRecipeOrderByViewRows(any());



    }
}