package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.IngredientCommand;
import com.sencerseven.basittarifler.domain.Ingredient;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class IngredientCommandToIngredientConverterTest {

    IngredientCommandToIngredientConverter ingredientCommandToIngredientConverter;

    @Mock
    IngredientDetailsCommandToIngredienDetailsConverter ingredientDetailsCommandToIngredienDetailsConverter;

    private final static Long ID = 1L;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ingredientCommandToIngredientConverter = new IngredientCommandToIngredientConverter(ingredientDetailsCommandToIngredienDetailsConverter);
    }

    @Test
    public void nullTest() {
        assertNull(ingredientCommandToIngredientConverter.convert(null));
    }

    public void emptyTest() {
        assertNotNull(ingredientCommandToIngredientConverter.convert(new IngredientCommand()));
    }


    @Test
    public void convert() {
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ID);

        Ingredient ingredient = ingredientCommandToIngredientConverter.convert(ingredientCommand);

        assertEquals(ID, ingredient.getId());

    }
}