package com.sencerseven.basittarifler.repository;

import com.sencerseven.basittarifler.domain.Recipe;
import com.sencerseven.basittarifler.domain.RecipeStats;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@DataJpaTest
public class RecipeStatsRepositoryTest {

    @Autowired
    RecipeStatsRepository recipeStatsRepository;

    private static final Long ID = 1L;

    @Test
    public void findRecipeStatsByRecipe() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<RecipeStats> recipeStats = recipeStatsRepository.findRecipeStatsByRecipe(recipe);

        assertEquals(recipeStats.get().getRecipe().getId(),ID);


    }
}