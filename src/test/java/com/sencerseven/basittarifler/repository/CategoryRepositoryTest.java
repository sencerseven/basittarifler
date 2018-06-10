package com.sencerseven.basittarifler.repository;

import com.sencerseven.basittarifler.domain.Category;
import com.sencerseven.basittarifler.domain.Recipe;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    public void findByCategoryDescriptionTest(){
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Optional<Category> category = categoryRepository.findByCategoryDescription("Öğlen Yemeği");

        assertEquals(recipe.getId(),category.get().getId());

    }


    @Test
    public void findCategoriesByMenuActive() throws Exception{

        List<Category> categories = categoryRepository.findCategoriesByMenuActive(PageRequest.of(0,1),true);

        assertTrue(categories.get(0).isMenuActive());

    }

    @Test
    public void findCategoriesByIdAndAndCategoryUrl() throws Exception{
        Optional<Category> categories = categoryRepository.findCategoriesByIdAndCategoryUrl(1L,"oglen-yemegi");

        assertNotNull(categories.isPresent());
    }


}