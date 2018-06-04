package com.sencerseven.basittarifler.controller;

import com.sencerseven.basittarifler.command.UsersCommand;
import com.sencerseven.basittarifler.domain.*;
import com.sencerseven.basittarifler.service.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.omg.CORBA.INTERNAL;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


public class RecipeControllerTest {

    @Mock
    RecipeService recipeService;

    @Mock
    RecipeStepsService recipeStepsService;

    @Mock
    RecipeTipsService recipeTipsService;

    @Mock
    CommentService commentService;

    @Mock
    RecipeStatsService recipeStatsService;

    @Mock
    Model model;

    RecipeController recipeController;

    private static final Long ID = 1L;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeController = new RecipeController(recipeService,recipeStepsService,recipeTipsService,commentService,recipeStatsService);

    }

    @Test
    public void testMockMVC() throws Exception{
        MockMvc mock = MockMvcBuilders.standaloneSetup(recipeController).build();

        when(recipeService.getFindById(any(Long.class))).thenReturn(new Recipe());

        mock.perform(get("/recipe/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void indexAction(){
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Users users = new Users();
        users.setId(1L);

        recipe.setUsers(users);

        List<RecipeSteps> recipeStepsList = new ArrayList<>();
        RecipeSteps recipeSteps = new RecipeSteps();
        recipeSteps.setId(1L);

        recipeStepsList.add(recipeSteps);

        List<RecipeTips> recipeTipsList = new ArrayList<>();
        RecipeTips recipeTips = new RecipeTips();
        recipeTips.setId(1L);

        recipeTipsList.add(recipeTips);

        List<Recipe> recipeList = new ArrayList<>();
        recipeList.add(recipe);

        Comment comment = new Comment();
        comment.setId(1L);

        List<Comment> commentList = new ArrayList<>();
        commentList.add(comment);

        UsersCommand usersCommand = new UsersCommand();
        usersCommand.setId(1L);

        //when
        when(recipeService.getFindById(ID)).thenReturn(recipe);
        when(recipeService.getRecipeOrderByViewCountAndLimit(any(Integer.class),any(Integer.class))).thenReturn(recipeList);
        when(recipeService.getRecipesByUsers(anyInt(),anyInt(),any(Users.class))).thenReturn(recipeList);
        when(recipeStepsService.getByRecipeOrderByViewRows(recipe)).thenReturn(recipeStepsList);
        when(recipeTipsService.getByRecipeOrderByViewRows(recipe)).thenReturn(recipeTipsList);
        when(commentService.getCommentsByRecipeOrderByCreatedAtAsc(any())).thenReturn(commentList);

        //then

        ArgumentCaptor<Recipe> recipeArgumentCaptor = ArgumentCaptor.forClass(Recipe.class);

        ArgumentCaptor<List<Recipe>> recipeListArgumentCaptor = ArgumentCaptor.forClass(List.class);
        ArgumentCaptor<List<Recipe>> recipeUserListArgumentCaptor = ArgumentCaptor.forClass(List.class);
        ArgumentCaptor<List<RecipeSteps>> recipeStepsArgumentCaptor = ArgumentCaptor.forClass(List.class);
        ArgumentCaptor<List<RecipeTips>> recipeTipsArgumentCaptor = ArgumentCaptor.forClass(List.class);
        ArgumentCaptor<List<Comment>> commentArgumentCaptor = ArgumentCaptor.forClass(List.class);

        String view = recipeController.indexAction(usersCommand,ID.toString(),model);

        assertEquals("index",view);
        verify(recipeService,times(1)).getRecipeOrderByViewCountAndLimit(any(Integer.class),any(Integer.class));
        verify(model,times(1)).addAttribute(eq("recipePopuler"),recipeListArgumentCaptor.capture());

        verify(recipeService,times(1)).getFindById(anyLong());
        verify(model,times(1)).addAttribute(eq("recipe"),recipeArgumentCaptor.capture());

        verify(recipeService,times(1)).getRecipesByUsers(anyInt(),anyInt(),any(Users.class));
        verify(model,times(1)).addAttribute(eq("recipesUsers"),recipeUserListArgumentCaptor.capture());

        verify(recipeStepsService,times(1)).getByRecipeOrderByViewRows(recipe);
        verify(model,times(1)).addAttribute(eq("recipeStepList"),(recipeStepsArgumentCaptor.capture()));


        verify(recipeTipsService,times(1)).getByRecipeOrderByViewRows(recipe);
        verify(model,times(1)).addAttribute(eq("recipeTipsList"),(recipeTipsArgumentCaptor.capture()));

        verify(commentService,times(1)).getCommentsByRecipeOrderByCreatedAtAsc(any());
        verify(model,times(1)).addAttribute(eq("recipeComment"),(commentArgumentCaptor.capture()));

        Recipe recipeCapture = recipeArgumentCaptor.getValue();

        List<Recipe> recipeListCapture = recipeListArgumentCaptor.getValue();
        List<Recipe> recipeUserListCapture = recipeUserListArgumentCaptor.getValue();
        List<RecipeSteps> recipeStepsCapture = recipeStepsArgumentCaptor.getValue();
        List<RecipeTips> recipeTipsCapture = recipeTipsArgumentCaptor.getValue();
        List<Comment> commentCapture = commentArgumentCaptor.getValue();

        assertEquals(ID,recipeCapture.getId());

        assertEquals(1,recipeListCapture.size());
        assertEquals(1,recipeUserListCapture.size());
        assertEquals(1,recipeStepsCapture.size());
        assertEquals(1,recipeTipsCapture.size());
        assertEquals(1,commentCapture.size());


    }

}