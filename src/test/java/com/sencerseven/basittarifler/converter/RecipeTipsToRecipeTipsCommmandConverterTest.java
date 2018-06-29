package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.RecipeTipsCommand;
import com.sencerseven.basittarifler.domain.RecipeTips;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class RecipeTipsToRecipeTipsCommmandConverterTest {

    RecipeTipsToRecipeTipsCommmandConverter recipeTipsToRecipeTipsCommmandConverter;

    private static final Long ID = 1L;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeTipsToRecipeTipsCommmandConverter = new RecipeTipsToRecipeTipsCommmandConverter();
    }

    @Test
    public void nullTest(){
        assertNull(recipeTipsToRecipeTipsCommmandConverter.convert(null));
    }

    @Test
    public void emptyTest(){
        assertNotNull(recipeTipsToRecipeTipsCommmandConverter.convert(new RecipeTips()));
    }

    @Test
    public void convert() {

        RecipeTips recipeTips = new RecipeTips();
        recipeTips.setId(ID);

        RecipeTipsCommand recipeTipsCommand = recipeTipsToRecipeTipsCommmandConverter.convert(recipeTips);


        assertEquals(recipeTipsCommand.getId(),ID);
    }
}