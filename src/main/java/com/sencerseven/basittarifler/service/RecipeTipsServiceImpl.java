package com.sencerseven.basittarifler.service;

import com.sencerseven.basittarifler.domain.Recipe;
import com.sencerseven.basittarifler.domain.RecipeSteps;
import com.sencerseven.basittarifler.domain.RecipeTips;
import com.sencerseven.basittarifler.repository.RecipeStepsRepository;
import com.sencerseven.basittarifler.repository.RecipeTipsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeTipsServiceImpl implements RecipeTipsService {

    RecipeTipsRepository recipeTipsRepository;

    public RecipeTipsServiceImpl(RecipeTipsRepository recipeTipsRepository) {
        this.recipeTipsRepository = recipeTipsRepository;
    }

    @Override
    public List<RecipeTips> getByRecipeOrderByViewRows(Recipe recipe) {
        List<RecipeTips> recipeTipsList = recipeTipsRepository.findByRecipeOrderByViewRows(recipe);
        return recipeTipsList;
    }
}
