package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.CategoryCommand;
import com.sencerseven.basittarifler.command.RecipeCommand;
import com.sencerseven.basittarifler.command.RecipeStepsCommand;
import com.sencerseven.basittarifler.domain.Category;
import com.sencerseven.basittarifler.domain.Recipe;
import com.sencerseven.basittarifler.domain.RecipeSteps;
import com.sencerseven.basittarifler.service.CategoryService;
import com.sencerseven.basittarifler.service.S3Services;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class RecipeCommandToRecipeConverterTest {

    RecipeCommandToRecipeConverter converter;
    CategoryCommandToCategoryConverter categoryCommandToCategoryConverter;
    RecipeStepsCommandToRecipeStepsConverter recipeStepsCommandToRecipeStepsConverter;

    @Mock
    NutritionCommandToNutritionConverter nutritionCommandToNutritionConverter;

    @Mock
    RecipeTipsCommandToRecipeTipsConverter recipeTipsCommandToRecipeTipsConverter;

    @Mock
    RecipeImagesCommandToRecipeImagesConverter recipeImagesCommandToRecipeImagesConverter;

    @Mock
    IngredientCommandToIngredientConverter ingredientCommandToIngredientConverter;

    @Mock
    CategoryService categoryService;

    @Mock
    S3Services s3Services;

    public static final Long ID= 1L;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        categoryCommandToCategoryConverter = new CategoryCommandToCategoryConverter(categoryService);
        recipeStepsCommandToRecipeStepsConverter = new RecipeStepsCommandToRecipeStepsConverter(s3Services);
        converter = new RecipeCommandToRecipeConverter(categoryCommandToCategoryConverter,recipeStepsCommandToRecipeStepsConverter,nutritionCommandToNutritionConverter,recipeTipsCommandToRecipeTipsConverter,recipeImagesCommandToRecipeImagesConverter,ingredientCommandToIngredientConverter,categoryService);

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

        RecipeStepsCommand recipeStepsCommand = new RecipeStepsCommand();
        recipeStepsCommand.setId(ID);
        recipeCommand.getRecipeSteps().add(recipeStepsCommand);

        Recipe recipe = converter.convert(recipeCommand);

        assertEquals(ID,recipe.getId());
        assertEquals(recipe.getCategories().size(),1);
        assertEquals(recipe.getRecipeSteps().size(),1);


    }


}