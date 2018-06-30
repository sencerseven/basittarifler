package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.RecipeImagesCommand;
import com.sencerseven.basittarifler.domain.RecipeImages;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class RecipeImagesToRecipeImagesCommandConverterTest {

    RecipeImagesToRecipeImagesCommandConverter recipeImagesToRecipeImagesCommandConverter;

    private final static Long ID = 1L;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeImagesToRecipeImagesCommandConverter = new RecipeImagesToRecipeImagesCommandConverter();
    }

    @Test
    public void nullTest() {
        assertNull(recipeImagesToRecipeImagesCommandConverter.convert(null));
    }

    @Test
    public void emptyTest() {
        assertNotNull(recipeImagesToRecipeImagesCommandConverter.convert(new RecipeImages()));
    }

    @Test
    public void convert() {
        RecipeImages recipeImages = new RecipeImages();
        recipeImages.setId(ID);

        RecipeImagesCommand recipeImagesCommand = recipeImagesToRecipeImagesCommandConverter.convert(recipeImages);

        assertEquals(ID, recipeImagesCommand.getId());
    }
}