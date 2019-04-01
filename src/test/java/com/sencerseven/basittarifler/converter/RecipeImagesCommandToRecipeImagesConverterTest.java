package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.RecipeImagesCommand;
import com.sencerseven.basittarifler.domain.RecipeImages;
import com.sencerseven.basittarifler.service.S3Services;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


public class RecipeImagesCommandToRecipeImagesConverterTest {

    RecipeImagesCommandToRecipeImagesConverter recipeImagesCommandToRecipeImagesConverter;

    @Mock
    S3Services s3Services;

    private static final Long ID = 1L;
    private static final String string = "test";

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeImagesCommandToRecipeImagesConverter = new RecipeImagesCommandToRecipeImagesConverter(s3Services);
    }

    @Test
    public void nullTest(){
        assertNull(recipeImagesCommandToRecipeImagesConverter.convert(null));
    }

    @Test
    public void emptyTest(){
        assertNotNull(recipeImagesCommandToRecipeImagesConverter.convert(new RecipeImagesCommand()));
    }

    @Test
    public void convert() {
        RecipeImagesCommand recipeImagesCommand = new RecipeImagesCommand();
        recipeImagesCommand.setId(ID);

        when(s3Services.uploadFile(any(),any(),any())).thenReturn(string);

        RecipeImages recipeImages = recipeImagesCommandToRecipeImagesConverter.convert(recipeImagesCommand);

        assertEquals(ID,recipeImages.getId());



    }


}