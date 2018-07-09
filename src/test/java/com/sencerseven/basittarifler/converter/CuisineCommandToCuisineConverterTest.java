package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.CuisineCommand;
import com.sencerseven.basittarifler.domain.Cuisine;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class CuisineCommandToCuisineConverterTest {

    CuisineCommandToCuisineConverter cuisineCommandToCuisineConverter;

    private static final Long ID = 1L;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        cuisineCommandToCuisineConverter = new CuisineCommandToCuisineConverter();

    }

    @Test
    public void nullTest() {
        assertNull(cuisineCommandToCuisineConverter.convert(null));
    }

    @Test
    public void emptyTest() {
        assertNotNull(cuisineCommandToCuisineConverter.convert(new CuisineCommand()));
    }

    @Test
    public void convert() {
        CuisineCommand cuisineCommand = new CuisineCommand();
        cuisineCommand.setId(ID);

        Cuisine cuisine = cuisineCommandToCuisineConverter.convert(cuisineCommand);

        assertEquals(cuisineCommand.getId(), cuisine.getId());
    }
}