package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.CategoryCommand;
import com.sencerseven.basittarifler.command.RecipeCommand;
import com.sencerseven.basittarifler.command.RecipeStepsCommand;
import com.sencerseven.basittarifler.domain.Category;
import com.sencerseven.basittarifler.domain.Recipe;
import com.sencerseven.basittarifler.domain.RecipeSteps;
import com.sencerseven.basittarifler.functions.BasitTariflerHelpers;
import com.sencerseven.basittarifler.model.MultipartImage;
import com.sencerseven.basittarifler.service.CategoryService;
import com.sencerseven.basittarifler.service.CuisineService;
import com.sencerseven.basittarifler.service.S3Services;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RecipeCommandToRecipeConverterTest {

    @Mock
    CategoryCommandToCategoryConverter categoryCommandToCategoryConverter;
    @Mock
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
    CuisineCommandToCuisineConverter cuisineCommandToCuisineConverter;
    @Mock
    CuisineService cuisineService;
    @Mock
    TagsCommandToTagsConverter tagsCommandToTagsConverter;
    @Mock
    BasitTariflerHelpers basitTariflerHelpers;
    @InjectMocks
    RecipeCommandToRecipeConverter converter;

    public static final Long ID= 1L;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void nullTest(){
        assertNull(converter.convert(null));

    }

    @Test
    public void emptyTest(){
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
        recipeStepsCommand.setImgURL("test.jpg");
        recipeCommand.getRecipeSteps().add(recipeStepsCommand);


        Recipe recipe = converter.convert(recipeCommand);

        assertEquals(recipe.getId(),ID);
        assertEquals(recipe.getCategories().size(),1);
        assertNotNull(recipe.getRecipeSteps());


    }


}