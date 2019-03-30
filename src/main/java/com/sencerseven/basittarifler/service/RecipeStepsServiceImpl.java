package com.sencerseven.basittarifler.service;

import com.sencerseven.basittarifler.domain.Recipe;
import com.sencerseven.basittarifler.domain.RecipeSteps;
import com.sencerseven.basittarifler.repository.RecipeStepsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeStepsServiceImpl implements RecipeStepsService {

    RecipeStepsRepository recipeStepsRepository;

    public RecipeStepsServiceImpl(RecipeStepsRepository recipeStepsRepository) {
        this.recipeStepsRepository = recipeStepsRepository;
    }

    @Override
    public List<RecipeSteps> getByRecipeOrderByViewRows(Recipe recipe) {
        List<RecipeSteps> recipeSteps =recipeStepsRepository.findByRecipeOrderByViewRows(recipe);
        return recipeSteps;

    }
}
