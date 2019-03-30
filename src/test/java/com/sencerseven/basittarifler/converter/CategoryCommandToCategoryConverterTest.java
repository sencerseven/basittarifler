package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.CategoryCommand;
import com.sencerseven.basittarifler.domain.Category;
import com.sencerseven.basittarifler.functions.BasitTariflerHelpers;
import com.sencerseven.basittarifler.service.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CategoryCommandToCategoryConverterTest {

    public static final Long ID = 1L;

    @Mock
    BasitTariflerHelpers basitTariflerHelpers;

    @InjectMocks
    CategoryCommandToCategoryConverter categoryConverter;

    @Mock
    CategoryService categoryService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void nullTest(){
        assertNull(categoryConverter.convert(null));
    }

    @Test
    public void emptyTest(){
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