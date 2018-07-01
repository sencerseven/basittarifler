package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.IngredientDetailsCommand;
import com.sencerseven.basittarifler.domain.IngredientDetails;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class IngredientDetailsCommandToIngredienDetailsConverterTest {

    IngredientDetailsCommandToIngredienDetailsConverter ingredientDetailsCommandToIngredienDetailsConverter;

    private final static Long ID =1L;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ingredientDetailsCommandToIngredienDetailsConverter = new IngredientDetailsCommandToIngredienDetailsConverter();
    }

    @Test
    public void nullTest(){
        assertNull(ingredientDetailsCommandToIngredienDetailsConverter.convert(null));
    }

    @Test
    public void emptyTest(){
        assertNotNull(ingredientDetailsCommandToIngredienDetailsConverter.convert(new IngredientDetailsCommand()));
    }

    @Test
    public void convert() {
        IngredientDetailsCommand ingredientDetailsCommand = new IngredientDetailsCommand();
        ingredientDetailsCommand.setId(ID);
        IngredientDetails ingredientDetails = ingredientDetailsCommandToIngredienDetailsConverter.convert(ingredientDetailsCommand);

        assertEquals(ID,ingredientDetails.getId());


    }
}