package com.sencerseven.basittarifler.repository;

import com.sencerseven.basittarifler.domain.Recipe;
import com.sencerseven.basittarifler.domain.RecipeStats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecipeStatsRepository extends JpaRepository<RecipeStats,Long> {

    Optional<RecipeStats> findRecipeStatsByRecipe(Recipe recipe);
}
