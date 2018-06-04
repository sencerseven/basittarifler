package com.sencerseven.basittarifler.repository;

import com.sencerseven.basittarifler.domain.Comment;
import com.sencerseven.basittarifler.domain.Recipe;
import com.sencerseven.basittarifler.domain.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    public void findCommentsByUsersAndRecipeOrderByCreated_atDesc() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        List<Comment> commentList = commentRepository.findCommentsByRecipeOrderByCreatedAtDesc(recipe);

        assertEquals(1,commentList.size());

    }

    @Test
    public void findCommentsByRecipeOrderByCreatedAtAsc() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        List<Comment> commentList = commentRepository.findCommentsByRecipeOrderByCreatedAtAsc(recipe);

        assertEquals(1,commentList.size());


    }
}