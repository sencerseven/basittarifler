package com.sencerseven.basittarifler.repository;

import com.sencerseven.basittarifler.domain.Recipe;
import com.sencerseven.basittarifler.domain.RecipeTips;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RecipeTipsRepositoryTest {

    @Autowired
    RecipeTipsRepository recipeTipsRepository;

    @Test
    public void findByRecipeOrderByViewRows() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        List<RecipeTips> recipeTipsList = recipeTipsRepository.findByRecipeOrderByViewRows(recipe);
        assertEquals(1,recipeTipsList.size());

    }
}