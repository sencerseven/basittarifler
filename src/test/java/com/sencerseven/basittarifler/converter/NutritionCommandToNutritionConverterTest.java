package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.NutritionCommand;
import com.sencerseven.basittarifler.domain.Nutrition;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.junit.Assert.*;

public class NutritionCommandToNutritionConverterTest {

    NutritionCommandToNutritionConverter nutritionCommandToNutritionConverter;

    @Spy
    NutritionCommand nutritionCommand;

    private static final Long ID = 1L;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        nutritionCommandToNutritionConverter = new NutritionCommandToNutritionConverter();
    }

    @Test
    public void nullTest(){ assertNull(nutritionCommandToNutritionConverter.convert(null));}

    @Test
    public void emptyTest(){ assertNotNull(nutritionCommandToNutritionConverter.convert(nutritionCommand));}

    @Test
    public void convert() {
        nutritionCommand.setId(ID);

        Nutrition nutrition = nutritionCommandToNutritionConverter.convert(nutritionCommand);

        assertEquals(nutritionCommand.getId(),nutrition.getId());
    }
}