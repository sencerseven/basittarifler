package com.sencerseven.basittarifler.service;

import com.sencerseven.basittarifler.domain.Recipe;
import com.sencerseven.basittarifler.domain.RecipeTips;

import java.util.List;

public interface RecipeTipsService {

    List<RecipeTips> getByRecipeOrderByViewRows(Recipe recipe);
}
