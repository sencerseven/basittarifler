package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.RecipeStepsCommand;
import com.sencerseven.basittarifler.domain.RecipeSteps;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeStepsCommandToRecipeStepsConverterTest {

    RecipeStepsCommandToRecipeStepsConverter recipeStepsCommandToRecipeStepsConverter;

    @Before
    public void setUp() throws Exception {
        recipeStepsCommandToRecipeStepsConverter = new RecipeStepsCommandToRecipeStepsConverter();
    }

    @Test
    public void nullTest(){
        assertNull(recipeStepsCommandToRecipeStepsConverter.convert(null));
    }

    @Test
    public void emptyTest(){
        assertNotNull(recipeStepsCommandToRecipeStepsConverter.convert(new RecipeStepsCommand()));
    }


    @Test
    public void convert() {
        RecipeStepsCommand recipeStepsCommand = new RecipeStepsCommand();
        recipeStepsCommand.setId(1L);

        RecipeSteps recipeSteps = recipeStepsCommandToRecipeStepsConverter.convert(recipeStepsCommand);


        assertEquals(recipeStepsCommand.getId(),recipeSteps.getId());

    }
}