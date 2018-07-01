package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.IngredientDetailsCommand;
import com.sencerseven.basittarifler.domain.IngredientDetails;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class IngredientDetailsToIngredientDetailsCommandConverterTest {

    IngredientDetailsToIngredientDetailsCommandConverter ingredientDetailsToIngredientDetailsCommandConverter;

    private final static Long ID = 1L;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ingredientDetailsToIngredientDetailsCommandConverter = new IngredientDetailsToIngredientDetailsCommandConverter();
    }

    @Test
    public void nullTest(){
        assertNull(ingredientDetailsToIngredientDetailsCommandConverter.convert(null));
    }

    @Test
    public void emptyTest(){
        assertNotNull(ingredientDetailsToIngredientDetailsCommandConverter.convert(new IngredientDetails()));
    }

    @Test
    public void convert() {
        IngredientDetails ingredientDetails = new IngredientDetails();
        ingredientDetails.setId(ID);

        IngredientDetailsCommand ingredientDetailsCommand= ingredientDetailsToIngredientDetailsCommandConverter.convert(ingredientDetails);

        assertEquals(ID,ingredientDetailsCommand.getId());
    }
}