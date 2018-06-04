package com.sencerseven.basittarifler.repository;

import com.sencerseven.basittarifler.domain.Comment;
import com.sencerseven.basittarifler.domain.Recipe;
import com.sencerseven.basittarifler.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findCommentsByRecipeOrderByCreatedAtDesc(Recipe recipe);
    List<Comment> findCommentsByRecipeOrderByCreatedAtAsc(Recipe recipe);
}
