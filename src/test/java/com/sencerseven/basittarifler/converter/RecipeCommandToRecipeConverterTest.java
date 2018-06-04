package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.CategoryCommand;
import com.sencerseven.basittarifler.command.RecipeCommand;
import com.sencerseven.basittarifler.domain.Category;
import com.sencerseven.basittarifler.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeCommandToRecipeConverterTest {

    RecipeCommandToRecipeConverter converter;
    CategoryCommandToCategoryConverter categoryCommandToCategoryConverter;

    public static final Long ID= 1L;


    @Before
    public void setUp() throws Exception {
        categoryCommandToCategoryConverter = new CategoryCommandToCategoryConverter();
        converter = new RecipeCommandToRecipeConverter(categoryCommandToCategoryConverter);

    }

    @Test
    public void testNullObject(){
        assertNull(converter.convert(null));

    }

    @Test
    public void testEmptyObject(){
        assertNotNull(converter.convert(new RecipeCommand()));
    }

    @Test
    public void convert(){
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(ID);

        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(ID);
        recipeCommand.getCategories().add(categoryCommand);

        Recipe recipe = converter.convert(recipeCommand);

        assertEquals(ID,recipe.getId());
        assertEquals(recipe.getCategories().size(),1);


    }


}