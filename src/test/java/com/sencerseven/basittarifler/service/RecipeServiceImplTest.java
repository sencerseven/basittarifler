package com.sencerseven.basittarifler.service;

import com.sencerseven.basittarifler.command.RecipeCommand;
import com.sencerseven.basittarifler.command.UsersCommand;
import com.sencerseven.basittarifler.converter.RecipeCommandToRecipeConverter;
import com.sencerseven.basittarifler.converter.RecipeToRecipeCommandConverter;
import com.sencerseven.basittarifler.converter.UsersCommandToUsersConverter;
import com.sencerseven.basittarifler.domain.Recipe;
import com.sencerseven.basittarifler.domain.Users;
import com.sencerseven.basittarifler.repository.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RecipeServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    CategoryService categoryService;

    @Mock
    RecipeToRecipeCommandConverter recipeToRecipeCommandConverter;

    @Mock
    RecipeCommandToRecipeConverter recipeCommandToRecipeConverter;

    @Mock
    UsersCommandToUsersConverter usersCommandToUsersConverter;

    @InjectMocks
    RecipeServiceImpl recipeService;

    public static final Long ID = 1L;
    public static final Long ID2 = 2L;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void getAllRecipes() {
        Recipe recipe = new Recipe();
        List recipes = new ArrayList();
        recipes.add(recipe);


        when(recipeService.getAllRecipes()).thenReturn(recipes);
        List<Recipe> recipeSet = recipeService.getAllRecipes();

        assertEquals(recipeSet.size(), 1);
        verify(recipeRepository, times(1)).findAll();

    }

    @Test
    public void getLimitRecipes() {
        Recipe recipe = new Recipe();
        List recipes = new ArrayList();
        recipes.add(recipe);

        when(recipeService.getLimitRecipes(PageRequest.of(0, 1))).thenReturn(recipes);

        List<Recipe> recipeSet = recipeService.getLimitRecipes(PageRequest.of(0, 1));


        verify(recipeRepository, times(1)).findRecipesBy(PageRequest.of(0, 1));

    }

    @Test
    public void getFindById() {
        Recipe recipe = new Recipe();
        recipe.setId(ID);

        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(ID)).thenReturn(recipeOptional);

        Recipe recipeResult = recipeService.getFindById(ID);

        verify(recipeRepository, times(1)).findById(anyLong());
        assertEquals(ID, recipeResult.getId());

    }

    @Test
    public void getRecipesByUsers() {
        Users users = new Users();
        users.setId(ID);

        Recipe recipe = new Recipe();
        recipe.setId(ID);
        List<Recipe> recipeList = new ArrayList<>();
        recipeList.add(recipe);

        when(recipeRepository.findRecipesByUsers(any(PageRequest.class), any(Users.class))).thenReturn(recipeList);

        List<Recipe> recipeListResponse = recipeService.getRecipesByUsers(0, 3, users);

        verify(recipeRepository, times(1)).findRecipesByUsers(any(), any());
        assertEquals(1, recipeListResponse.size());


    }


    @Test
    public void updateByUserCommand() {

        Recipe recipe = new Recipe();
        recipe.setId(ID);
        recipe.setUsers(new Users());
        recipe.getUsers().setId(ID);


        UsersCommand usersCommand = new UsersCommand();
        usersCommand.setId(ID);

        when(recipeRepository.save(any(Recipe.class))).thenReturn(recipe);
        Recipe recipeNull = recipeService.updateByUserCommand(null, null);
        Recipe recipeNotEqual = recipeService.updateByUserCommand(recipe, usersCommand);

        recipe.getUsers().setId(ID2);

        Recipe recipeResult = recipeService.updateByUserCommand(recipe, usersCommand);


        assertNull(recipeNull);
        assertNull(recipeNotEqual);
        assertEquals(ID2, recipeResult.getUsers().getId());
        verify(recipeRepository, times(1)).save(any(Recipe.class));


    }

    @Test
    public void saveRecipeCommand() {
        Recipe recipe = new Recipe();
        recipe.setId(ID);

        UsersCommand usersCommand = new UsersCommand();
        usersCommand.setId(ID);

        Users users = new Users();
        users.setId(ID);

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(ID);

        when(recipeRepository.save(any(Recipe.class))).thenReturn(recipe);
        when(usersCommandToUsersConverter.convert(any())).thenReturn(users);
        when(recipeCommandToRecipeConverter.convert(any())).thenReturn(recipe);


        RecipeCommand recipeNull = recipeService.saveRecipeCommand(null, null);
        RecipeCommand recipeNotNull = recipeService.saveRecipeCommand(recipeCommand, usersCommand);


        assertNull(recipeNull);
        assertEquals(recipe.getId(),ID);

        verify(recipeRepository, times(1)).save(any(Recipe.class));


    }
}