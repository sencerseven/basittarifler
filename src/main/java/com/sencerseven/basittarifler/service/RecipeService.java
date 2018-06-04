package com.sencerseven.basittarifler.service;

import com.sencerseven.basittarifler.command.RecipeCommand;
import com.sencerseven.basittarifler.domain.Recipe;
import com.sencerseven.basittarifler.domain.Users;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RecipeService {

    List<Recipe> getAllRecipes();

    List<Recipe> getLimitRecipes(Pageable pageable);

    Recipe getFindById(Long id);

    List<Recipe>getRecipeOrderByViewCountAndLimit(int page,int size);

    List<Recipe> getRecipesByUsers(int page,int size,Users user);

    RecipeCommand getCommandFindById(Long id);

    List<Recipe> getRecipeIdNotPopulerRecipe(int page,int size,Long id);

    List<Recipe> getAllPopulerRecipe(int page, int size);
}
