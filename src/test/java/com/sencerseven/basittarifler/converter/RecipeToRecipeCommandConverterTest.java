package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.CategoryCommand;
import com.sencerseven.basittarifler.command.RecipeCommand;
import com.sencerseven.basittarifler.domain.Category;
import com.sencerseven.basittarifler.domain.Recipe;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;

public class RecipeToRecipeCommandConverterTest {

    RecipeToRecipeCommandConverter converter;
    CategoryToCategoryCommandConverter categoryToCategoryCommandConverter;
    RecipeStepsToRecipeStepsCommandConverter recipeStepsToRecipeStepsCommandConverter;

    @Mock
    RecipeImagesToRecipeImagesCommandConverter recipeImagesToRecipeImagesCommandConverter;

    @Mock
    NutritionToNutritionCommandConverter nutritionToNutritionCommandConverter;

    IngredientToIngredientCommandConverter ingredientToIngredientCommandConverter;

    RecipeTipsToRecipeTipsCommmandConverter recipeTipsToRecipeTipsCommmandConverter;

    public static final Long ID= 1L;


    @Before
    public void setUp() throws Exception {
        categoryToCategoryCommandConverter = new CategoryToCategoryCommandConverter();
        recipeStepsToRecipeStepsCommandConverter = new RecipeStepsToRecipeStepsCommandConverter();
        converter = new RecipeToRecipeCommandConverter(categoryToCategoryCommandConverter,
                recipeStepsToRecipeStepsCommandConverter,
                recipeImagesToRecipeImagesCommandConverter,
                nutritionToNutritionCommandConverter,
                ingredientToIngredientCommandConverter,
                recipeTipsToRecipeTipsCommmandConverter);

    }

    @Test
    public void testNullObject(){
        assertNull(converter.convert(null));

    }

    @Test
    public void testEmptyObject(){
        assertNotNull(converter.convert(new Recipe()));
    }

    @Test
    public void convert(){
        Recipe recipe = new Recipe();
        recipe.setId(ID);

        Category category = new Category();
        category.setId(ID);

        recipe.getCategories().add(category);

        RecipeCommand recipeCommand = converter.convert(recipe);


        assertEquals(recipeCommand.getId(),ID);
        assertEquals(recipeCommand.getCategories().size(),1);

    }


}