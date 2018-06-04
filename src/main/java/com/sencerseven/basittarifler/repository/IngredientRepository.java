package com.sencerseven.basittarifler.repository;

import com.sencerseven.basittarifler.domain.IngredientDetails;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<IngredientDetails, Long> {
}
