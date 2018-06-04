package com.sencerseven.basittarifler.service;

import com.sencerseven.basittarifler.command.UsersCommand;
import com.sencerseven.basittarifler.domain.Recipe;
import com.sencerseven.basittarifler.domain.RecipeStats;

import java.util.Optional;

public interface RecipeStatsService {

    RecipeStats saveByUsersCommand(UsersCommand usersCommand, Recipe recipe);

}
