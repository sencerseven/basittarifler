package com.sencerseven.basittarifler.repository;

import com.sencerseven.basittarifler.domain.Category;
import com.sencerseven.basittarifler.domain.Recipe;
import com.sencerseven.basittarifler.domain.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RecipeRepositoryTest {

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    UserRepository userRepository;

    public static final Long ID = 1L;


    @Test
    public void findTop10ByOrderByIdDesc() {


       Page<Recipe> recipeList = recipeRepository.findAll(PageRequest.of(0,1));

       assertEquals(1,recipeList.getContent().size());
    }

    @Test
    public void findRecipes(){
        List<Recipe> recipeList = recipeRepository.findRecipesBy(PageRequest.of(0,2));
        assertEquals(2,recipeList.size());
    }

    @Test
    public void findById(){
        Optional<Recipe> recipeOptional = recipeRepository.findById(ID);
        assertEquals(recipeOptional.get().getId(),ID);
    }


    @Test
    public void findRecipesOrderByViewCount(){
        PageRequest request = PageRequest.of(0,1,Sort.Direction.DESC,"viewCount");
        List<Recipe> recipeList = recipeRepository.findRecipesBy(request);
        assertEquals(recipeList.size(),1);
    }

    @Test
    public void findRecipesByUsers(){
        Users users = userRepository.findByUserName("sencer").get();
    PageRequest request = PageRequest.of(0,1);
    List<Recipe> recipeList = recipeRepository.findRecipesByUsers(request,users);
    assertEquals(1,recipeList.size());

    }

    @Test
    public void findRecipeByRecipeNot(){

        Optional<Recipe> recipe = recipeRepository.findById(2L);
        PageRequest pageRequest = PageRequest.of(0,1);
        List<Recipe> recipeList = recipeRepository.findRecipeByIdNot(pageRequest,recipe.get().getId());
        assertEquals(recipeList.get(0).getId(),ID);

    }
    @Test
    public void findAllBy(){
        PageRequest pageRequest = PageRequest.of(0,2);
        List<Recipe> recipeList = recipeRepository.findAllByOrderByViewCountDesc(pageRequest);
        assertEquals(recipeList.size(),2);

    }
    @Test
    public void findAllByOrderByCreated_atDesc(){
        PageRequest pageRequest = PageRequest.of(0,2);
        Page recipe = recipeRepository.findAllByOrderByCreatedAtDesc(pageRequest);


        assertEquals(recipe.getContent().size(),2);

    }

    @Test
     public void findRecipeByCategories(){
        Category category = new Category();
        category.setId(4L);
        Category category2 = new Category();
        category2.setId(5L);
        Set<Category> categories = new HashSet<>();
        categories.add(category);
        categories.add(category2);
        Page<Recipe> recipeList = recipeRepository.findRecipeByCategoriesInOrderByCreatedAtDesc(PageRequest.of(0,2),categories);
        assertEquals(recipeList.getSize(),2);
     }

    @Test
    public void findRecipeByTagsContaining(){
        Page<Recipe> page = recipeRepository.findRecipesByTags_TagsNameContaining(PageRequest.of(1,2),"oldu");
        assertEquals(1,page.getSize());

    }
}