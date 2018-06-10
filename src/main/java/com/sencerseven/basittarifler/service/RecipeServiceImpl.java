package com.sencerseven.basittarifler.service;

import com.sencerseven.basittarifler.command.RecipeCommand;
import com.sencerseven.basittarifler.command.UsersCommand;
import com.sencerseven.basittarifler.converter.RecipeToRecipeCommandConverter;
import com.sencerseven.basittarifler.domain.Category;
import com.sencerseven.basittarifler.domain.Recipe;
import com.sencerseven.basittarifler.domain.Users;
import com.sencerseven.basittarifler.exceptions.NotFoundException;
import com.sencerseven.basittarifler.repository.RecipeRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl implements RecipeService {

    RecipeRepository recipeRepository;

    CategoryService categoryService;

    RecipeToRecipeCommandConverter recipeToRecipeCommandConverter;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeToRecipeCommandConverter recipeToRecipeCommandConverter,CategoryService categoryService) {
        this.recipeRepository = recipeRepository;
        this.recipeToRecipeCommandConverter = recipeToRecipeCommandConverter;
        this.categoryService = categoryService;
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @Override
    public List<Recipe> getLimitRecipes(Pageable pageable) {
     return recipeRepository.findRecipesBy(pageable);
    }

    @Override
    public Recipe getFindById(Long id){
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);
        if(!recipeOptional.isPresent())
            throw new NotFoundException("Recipe is Not Found!");

        return recipeOptional.get();
    }

    @Override
    public List<Recipe> getRecipeOrderByViewCountAndLimit(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page,size, Sort.Direction.DESC,"viewCount");
        return getLimitRecipes(pageRequest);
    }

    @Override
    public List<Recipe> getRecipesByUsers(int page, int size,@Nullable Users user) {
        List<Recipe> recipeList = recipeRepository.findRecipesByUsers(PageRequest.of(page,size),user);
        return recipeList;
    }

    @Override
    public RecipeCommand getCommandFindById(Long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);
        if(!recipeOptional.isPresent())
            throw new NotFoundException("Recipe is Not Found!");

        return recipeToRecipeCommandConverter.convert(recipeOptional.get());
    }

    @Override
    public List<Recipe> getRecipeIdNotPopulerRecipe(int page,int size, Long id) {
        return recipeRepository.findRecipeByIdNot(PageRequest.of(page,size,Sort.Direction.DESC,"recipeStats.totalView"),id);
    }

    @Cacheable(value = "getAllPopulerRecipe")
    @Override
    public List<Recipe> getAllPopulerRecipe(int page,int size) {
        return recipeRepository.findAllByOrderByViewCountDesc(PageRequest.of(page,size));
    }

    @Override
    public Recipe updateByUserCommand(Recipe recipe, UsersCommand usersCommand) {

        if(usersCommand == null || usersCommand.getId() == recipe.getUsers().getId())
            return null;

        recipe.setViewCount(recipe.getViewCount() + 1);


        return recipeRepository.save(recipe);
    }
    @Cacheable(value = "findAllByOrderByCreatedAtDesc")
    @Override
    public Page<Recipe> findAllByOrderByCreatedAtDesc(int page, int size) {
        PageRequest request = PageRequest.of(page,size);
        Page<Recipe> recipePage = recipeRepository.findAllByOrderByCreatedAtDesc(request);
        return recipePage;
    }

    @Override
    public Long countAllBy(){
        return recipeRepository.countAllBy();
    }

    @Override
    public Page<Recipe> findRecipeByCategoriesInOrderByCreatedAtDesc(int page, int size, Set<Category> categories) {

        return null;
    }

}
