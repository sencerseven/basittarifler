package com.sencerseven.basittarifler.repository;

import com.sencerseven.basittarifler.domain.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category,Long> {

    Optional<Category> findById(Long id);

    Optional<Category> findByCategoryDescription(String desc);

    List<Category> findCategoriesByMenuActive(Pageable pageable, Boolean active);

    Optional<Category> findCategoriesByIdAndCategoryUrl(Long catId, String catUrl);

    @Modifying
    @Query("delete from Category c where c.id = ?1")
    void deleteCategoryById(Long id);

}
