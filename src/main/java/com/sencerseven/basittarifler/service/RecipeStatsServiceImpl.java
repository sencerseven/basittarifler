package com.sencerseven.basittarifler.service;

import com.sencerseven.basittarifler.command.UsersCommand;
import com.sencerseven.basittarifler.domain.Recipe;
import com.sencerseven.basittarifler.domain.RecipeStats;
import com.sencerseven.basittarifler.repository.RecipeStatsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecipeStatsServiceImpl implements RecipeStatsService {

    RecipeStatsRepository recipeStatsRepository;

    public RecipeStatsServiceImpl(RecipeStatsRepository recipeStatsRepository) {
        this.recipeStatsRepository = recipeStatsRepository;
    }

    @Override
    public RecipeStats saveByUsersCommand(UsersCommand usersCommand, Recipe recipe) {

        Optional<RecipeStats> recipeStats =recipeStatsRepository.findRecipeStatsByRecipe(recipe);

        if(!recipeStats.isPresent())
            return null;

        RecipeStats recipeStatsDetach = recipeStats.get();
        if(usersCommand == null || usersCommand.getId() == recipeStatsDetach.getRecipe().getUsers().getId())
            return null;

        recipeStatsDetach.setTotalView(recipeStatsDetach.getTotalView() + 1);


        return recipeStatsRepository.save(recipeStatsDetach);
    }
}
