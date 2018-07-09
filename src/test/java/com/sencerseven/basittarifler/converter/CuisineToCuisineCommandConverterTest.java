package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.CuisineCommand;
import com.sencerseven.basittarifler.domain.Cuisine;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class CuisineToCuisineCommandConverterTest {

    CuisineToCuisineCommandConverter cuisineToCuisineCommandConverter;

    private static final Long ID = 1L;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        cuisineToCuisineCommandConverter = new CuisineToCuisineCommandConverter();
    }

    @Test
    public void nullTest(){
        assertNull(cuisineToCuisineCommandConverter.convert(null));
    }

    @Test
    public void emptyTest(){
        assertNotNull(cuisineToCuisineCommandConverter.convert(new Cuisine()));
    }


    @Test
    public void convert() {

        Cuisine cuisine = new Cuisine();
        cuisine.setId(ID);

        CuisineCommand cuisineCommand = cuisineToCuisineCommandConverter.convert(cuisine);

        assertEquals(cuisine.getId(),cuisineCommand.getId());

    }
}