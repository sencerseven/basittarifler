package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.IngredientCommand;
import com.sencerseven.basittarifler.domain.Ingredient;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class IngredientToIngredientCommandConverterTest {

    IngredientToIngredientCommandConverter ingredientToIngredientCommandConverter;

    @Mock
    IngredientDetailsToIngredientDetailsCommandConverter ingredientDetailsToIngredientDetailsCommandConverter;

    private static final Long ID = 1L;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ingredientToIngredientCommandConverter = new IngredientToIngredientCommandConverter(ingredientDetailsToIngredientDetailsCommandConverter);
    }

    @Test
    public void nullTest(){
        assertNull(ingredientToIngredientCommandConverter.convert(null));
    }

    @Test
    public void emptyTest(){
        assertNotNull(ingredientToIngredientCommandConverter.convert(new Ingredient()));
    }

    @Test
    public void convert() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID);



        IngredientCommand ingredientCommand = ingredientToIngredientCommandConverter.convert(ingredient);


        assertEquals(ID,ingredientCommand.getId());


    }
}