package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.NutritionCommand;
import com.sencerseven.basittarifler.domain.Nutrition;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.junit.Assert.*;

public class NutritionToNutritionCommandConverterTest {


    NutritionToNutritionCommandConverter nutritionToNutritionCommandConverter;

    @Spy
    Nutrition nutrition;

    private static final Long ID = 1L;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        nutritionToNutritionCommandConverter = new NutritionToNutritionCommandConverter();
    }

    @Test
    public void nullTest(){ assertNull(nutritionToNutritionCommandConverter.convert(null));}

    @Test
    public void emptyTest(){assertNotNull(nutritionToNutritionCommandConverter.convert(nutrition));}

    @Test
    public void convert() {

        nutrition.setId(ID);

        NutritionCommand nutritionCommand =nutritionToNutritionCommandConverter.convert(nutrition);

        assertEquals(nutrition.getId(),nutritionCommand.getId());

    }
}