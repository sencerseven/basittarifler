package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.RecipeStepsCommand;
import com.sencerseven.basittarifler.domain.RecipeSteps;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeStepsToRecipeStepsCommandConverterTest {

    RecipeStepsToRecipeStepsCommandConverter recipeStepsToRecipeStepsCommandConverter;

    @Before
    public void setUp() throws Exception {
        recipeStepsToRecipeStepsCommandConverter = new RecipeStepsToRecipeStepsCommandConverter();
    }

    @Test
    public void nullTest() {
        assertNull(recipeStepsToRecipeStepsCommandConverter.convert(null));
    }

    @Test
    public void emptyTest() {
        assertNotNull(recipeStepsToRecipeStepsCommandConverter.convert(new RecipeSteps()));
    }


    @Test
    public void convert() {
        RecipeSteps recipeSteps = new RecipeSteps();
        recipeSteps.setId(1L);

        RecipeStepsCommand recipeStepsCommand = recipeStepsToRecipeStepsCommandConverter.convert(recipeSteps);


        assertEquals(recipeSteps.getId(), recipeStepsCommand.getId());

    }
}