package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.RecipeTipsCommand;
import com.sencerseven.basittarifler.domain.RecipeTips;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class RecipeTipsCommandToRecipeTipsConverterTest {

    RecipeTipsCommandToRecipeTipsConverter recipeTipsCommandToRecipeTipsConverter;

    private final static Long ID = 1L;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeTipsCommandToRecipeTipsConverter = new RecipeTipsCommandToRecipeTipsConverter();
    }

    @Test
    public void nullTest(){
        recipeTipsCommandToRecipeTipsConverter.convert(null);
    }

    @Test
    public void emptyTest(){
        recipeTipsCommandToRecipeTipsConverter.convert(new RecipeTipsCommand());
    }

    @Test
    public void convert() {

        RecipeTipsCommand recipeTipsCommand = new RecipeTipsCommand();
        recipeTipsCommand.setId(ID);

        RecipeTips recipeTips = recipeTipsCommandToRecipeTipsConverter.convert(recipeTipsCommand);

        assertEquals(recipeTips.getId(),ID);

    }
}