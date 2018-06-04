package com.sencerseven.basittarifler.repository;

import com.sencerseven.basittarifler.domain.Recipe;
import com.sencerseven.basittarifler.domain.RecipeTips;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeTipsRepository extends CrudRepository<RecipeTips,Long> {

    List<RecipeTips> findByRecipeOrderByViewRows(Recipe recipe);


}
