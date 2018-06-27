package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.RecipeStepsCommand;
import com.sencerseven.basittarifler.domain.RecipeSteps;
import com.sencerseven.basittarifler.service.S3Services;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class RecipeStepsCommandToRecipeStepsConverterTest {

    RecipeStepsCommandToRecipeStepsConverter recipeStepsCommandToRecipeStepsConverter;

    @Mock
    S3Services s3Services;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeStepsCommandToRecipeStepsConverter = new RecipeStepsCommandToRecipeStepsConverter(s3Services);
    }

    @Test
    public void nullTest() {
        assertNull(recipeStepsCommandToRecipeStepsConverter.convert(null));
    }

    @Test
    public void emptyTest() {
        assertNotNull(recipeStepsCommandToRecipeStepsConverter.convert(new RecipeStepsCommand()));
    }


    @Test
    public void convert() {
        RecipeStepsCommand recipeStepsCommand = new RecipeStepsCommand();
        recipeStepsCommand.setId(1L);

        RecipeSteps recipeSteps = recipeStepsCommandToRecipeStepsConverter.convert(recipeStepsCommand);


        assertEquals(recipeStepsCommand.getId(), recipeSteps.getId());

    }
}