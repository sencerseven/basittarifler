package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.CategoryCommand;
import com.sencerseven.basittarifler.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryCommandToCategoryConverterTest {

    public static final Long ID = 1L;

    CategoryCommandToCategoryConverter categoryConverter;

    @Before
    public void setUp() throws Exception {
        categoryConverter = new CategoryCommandToCategoryConverter();
    }

    @Test
    public void testNullObject(){
        assertNull(categoryConverter.convert(null));
    }

    @Test
    public void testEmptyObject(){
        assertNotNull(categoryConverter.convert(new CategoryCommand()));
    }

    @Test
    public void convert() {
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(ID);

        Category category= categoryConverter.convert(categoryCommand);

        assertEquals(ID,category.getId());

    }
}