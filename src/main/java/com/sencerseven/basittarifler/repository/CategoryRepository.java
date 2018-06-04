package com.sencerseven.basittarifler.repository;

import com.sencerseven.basittarifler.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category,Long> {

    Optional<Category> findByCategoryDescription(String desc);
}
