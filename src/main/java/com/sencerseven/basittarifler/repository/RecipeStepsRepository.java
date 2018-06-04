package com.sencerseven.basittarifler.repository;

import com.sencerseven.basittarifler.domain.Recipe;
import com.sencerseven.basittarifler.domain.RecipeSteps;
import com.sun.tools.javac.util.RichDiagnosticFormatter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface RecipeStepsRepository extends JpaRepository<RecipeSteps,Long> {



    List<RecipeSteps> findByRecipeOrderByViewRows(Recipe recipe);



}
