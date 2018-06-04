package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.CategoryCommand;
import com.sencerseven.basittarifler.domain.Category;
import com.sencerseven.basittarifler.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;

public class CategoryToCategoryCommandConverterTest {

    CategoryToCategoryCommandConverter converter;

    public static final Long ID = 1L;


    @Before
    public void setUp() throws Exception {
        converter = new CategoryToCategoryCommandConverter();

    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));

    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    public void convert() {
        Category category = new Category();
        category.setId(ID);


        CategoryCommand categoryCommand = converter.convert(category);

        assertEquals(ID, categoryCommand.getId());

    }

}