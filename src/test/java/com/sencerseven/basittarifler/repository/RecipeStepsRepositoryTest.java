package com.sencerseven.basittarifler.repository;

import com.sencerseven.basittarifler.domain.Recipe;
import com.sencerseven.basittarifler.domain.RecipeSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RecipeStepsRepositoryTest {

    @Autowired
    RecipeStepsRepository recipeStepsRepository;


    @Test
    public void findByRecipeOrderByViewRows() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        List<RecipeSteps> recipeSteps = recipeStepsRepository.findByRecipeOrderByViewRows(recipe);
        assertEquals(2,recipeSteps.size());
    }
}