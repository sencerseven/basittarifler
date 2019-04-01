package com.sencerseven.basittarifler.service;

import com.sencerseven.basittarifler.domain.Recipe;
import com.sencerseven.basittarifler.domain.RecipeSteps;
import com.sencerseven.basittarifler.repository.RecipeStepsRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class RecipeStepsServiceImplTest {

    @Mock
    RecipeStepsRepository recipeStepsRepository;

    RecipeStepsServiceImpl recipeStepsService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeStepsService = new RecipeStepsServiceImpl(recipeStepsRepository);
    }

    @Test
    public void getByRecipeOrderByViewRows() {
        List<RecipeSteps> recipeSteps = new ArrayList<>();

        RecipeSteps recipestep = new RecipeSteps();
        recipestep.setId(1L);

        recipeSteps.add(recipestep);

        Recipe recipe = new Recipe();
        recipe.setId(1L);

        when(recipeStepsService.getByRecipeOrderByViewRows(recipe)).thenReturn(recipeSteps);
        List<RecipeSteps> recipeStepsList = recipeStepsService.getByRecipeOrderByViewRows(recipe);

        assertEquals(1,recipeStepsList.size());
        verify(recipeStepsRepository,times(1)).findByRecipeOrderByViewRows(recipe);



    }
}