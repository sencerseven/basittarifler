package com.sencerseven.basittarifler.repository;

import com.sencerseven.basittarifler.domain.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CategoryRepository extends CrudRepository<Category,Long> {

    Optional<Category> findByCategoryDescription(String desc);

    List<Category> findCategoriesByMenuActive(Pageable pageable, Boolean active);

    Optional<Category> findCategoriesByIdAndCategoryUrl(Long catId, String catUrl);

}
