package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.CategoryCommand;
import com.sencerseven.basittarifler.domain.Category;
import com.sencerseven.basittarifler.service.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class CategoryCommandToCategoryConverterTest {

    public static final Long ID = 1L;

    CategoryCommandToCategoryConverter categoryConverter;

    @Mock
    CategoryService categoryService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        categoryConverter = new CategoryCommandToCategoryConverter(categoryService);
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