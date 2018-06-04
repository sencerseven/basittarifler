package com.sencerseven.basittarifler.service;

import com.sencerseven.basittarifler.command.UsersCommand;
import com.sencerseven.basittarifler.domain.Recipe;
import com.sencerseven.basittarifler.domain.RecipeStats;
import com.sencerseven.basittarifler.repository.RecipeStatsRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RecipeStatsServiceImplTest {

    RecipeStatsServiceImpl recipeStatsService;

    @Mock
    RecipeStatsRepository recipeStatsRepository;

    private static final Long ID = 1L;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeStatsService = new RecipeStatsServiceImpl(recipeStatsRepository);
    }


    @Test
    public void saveByUsersCommand() {
        UsersCommand usersCommand = new UsersCommand();
        usersCommand.setId(ID);

        Recipe recipe = new Recipe();
        recipe.setId(ID);

        RecipeStats recipeStats = new RecipeStats();
        recipeStats.setRecipe(recipe);
        recipeStats.setTotalView(2L);
        Optional<RecipeStats> recipeStatsOptional = Optional.of(recipeStats);

        when(recipeStatsRepository.findRecipeStatsByRecipe(any(Recipe.class))).thenReturn(recipeStatsOptional);
        when(recipeStatsRepository.save(recipeStats)).thenReturn(recipeStats);


        RecipeStats resultRecipe = recipeStatsService.saveByUsersCommand(usersCommand,recipe);

        assertEquals(resultRecipe.getRecipe().getId(),recipeStats.getRecipe().getId());
        verify(recipeStatsRepository,times(1)).findRecipeStatsByRecipe(any(Recipe.class));
        verify(recipeStatsRepository,times(1)).save(any(RecipeStats.class));




    }
}