package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.RecipeImagesCommand;
import com.sencerseven.basittarifler.domain.RecipeImages;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class RecipeImagesCommandToRecipeImagesConverterTest {

    RecipeImagesCommandToRecipeImagesConverter recipeImagesCommandToRecipeImagesConverter;

    private static final Long ID = 1L;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeImagesCommandToRecipeImagesConverter = new RecipeImagesCommandToRecipeImagesConverter();
    }

    @Test
    public void nullTest(){
        assertNull(recipeImagesCommandToRecipeImagesConverter.convert(null));
    }

    public void emptyTest(){
        assertNotNull(recipeImagesCommandToRecipeImagesConverter.convert(new RecipeImagesCommand()));
    }

    @Test
    public void convert() {
        RecipeImagesCommand recipeImagesCommand = new RecipeImagesCommand();
        recipeImagesCommand.setId(ID);

        RecipeImages recipeImages = recipeImagesCommandToRecipeImagesConverter.convert(recipeImagesCommand);

        assertEquals(ID,recipeImages.getId());



    }
}