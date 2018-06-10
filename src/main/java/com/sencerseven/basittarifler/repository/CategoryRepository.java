package com.sencerseven.basittarifler.repository;

import com.sencerseven.basittarifler.domain.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category,Long> {

    Optional<Category> findByCategoryDescription(String desc);

    List<Category> findCategoriesByMenuActive(Pageable pageable, Boolean active);

    List<Category> findCategoriesByIdAndCategoryUrl(Pageable pageable, Long catId,String catUrl);

}
