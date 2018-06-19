package com.sencerseven.basittarifler.repository;

import com.sencerseven.basittarifler.domain.Recipe;
import com.sencerseven.basittarifler.domain.RecipeSteps;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeStepsRepository extends JpaRepository<RecipeSteps,Long> {



    List<RecipeSteps> findByRecipeOrderByViewRows(Recipe recipe);



}
