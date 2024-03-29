package com.sencerseven.basittarifler.repository;

import com.sencerseven.basittarifler.domain.Category;
import com.sencerseven.basittarifler.domain.Recipe;
import com.sencerseven.basittarifler.domain.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RecipeRepository extends JpaRepository<Recipe,Long> {

    Optional<Recipe> findById(Long Id);


    List<Recipe> findRecipesBy(Pageable pageable);

    List<Recipe> findRecipesByUsers(Pageable pageable, Users users);

    List<Recipe> findRecipeByIdNot(Pageable pageable,Long id);
    List<Recipe> findAllByOrderByViewCountDesc(Pageable pageable);

    Page<Recipe> findAllByOrderByCreatedAtDesc(Pageable pageable);
    Long countAllBy();

    Page<Recipe> findRecipeByCategoriesInOrderByCreatedAtDesc(Pageable pageable,Set<Category> categories);


    Page<Recipe> findRecipesByTags_TagsNameContaining(Pageable pageable,String tags);
    List<Recipe> findRecipeByTags_TagsNameContaining(String tags);



}
