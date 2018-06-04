package com.sencerseven.basittarifler.service;

import com.sencerseven.basittarifler.command.RecipeCommand;
import com.sencerseven.basittarifler.converter.RecipeToRecipeCommandConverter;
import com.sencerseven.basittarifler.domain.Recipe;
import com.sencerseven.basittarifler.domain.Users;
import com.sencerseven.basittarifler.exceptions.NotFoundException;
import com.sencerseven.basittarifler.repository.RecipeRepository;
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

    RecipeToRecipeCommandConverter recipeToRecipeCommandConverter;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeToRecipeCommandConverter recipeToRecipeCommandConverter) {
        this.recipeRepository = recipeRepository;
        this.recipeToRecipeCommandConverter = recipeToRecipeCommandConverter;
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

    @Override
    public List<Recipe> getAllPopulerRecipe(int page,int size) {
        return recipeRepository.findAllBy(PageRequest.of(page,size,Sort.Direction.DESC,"recipeStats.totalView"));
    }

}
