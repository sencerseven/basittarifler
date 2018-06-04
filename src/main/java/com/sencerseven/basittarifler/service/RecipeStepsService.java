package com.sencerseven.basittarifler.service;

import com.sencerseven.basittarifler.domain.Recipe;
import com.sencerseven.basittarifler.domain.RecipeSteps;

import java.util.List;

public interface RecipeStepsService {

    List<RecipeSteps> getByRecipeOrderByViewRows(Recipe recipe);

}
