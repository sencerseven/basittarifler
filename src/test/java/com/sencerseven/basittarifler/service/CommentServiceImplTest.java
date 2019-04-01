package com.sencerseven.basittarifler.service;

import com.sencerseven.basittarifler.converter.CommentCommandToCommentConverter;
import com.sencerseven.basittarifler.converter.CommentToCommentCommandConverter;
import com.sencerseven.basittarifler.converter.UsersCommandToUsersConverter;
import com.sencerseven.basittarifler.domain.Comment;
import com.sencerseven.basittarifler.domain.Recipe;
import com.sencerseven.basittarifler.repository.CommentRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CommentServiceImplTest {

    @Mock
    CommentRepository commentRepository;


    @Mock
    CommentCommandToCommentConverter commentCommandToCommentConverter;

    @Mock
    CommentToCommentCommandConverter commentToCommentCommandConverter;

    @Mock
    UsersCommandToUsersConverter usersCommandToUsersConverter;

    CommentServiceImpl commentService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        commentService = new CommentServiceImpl(commentRepository,commentCommandToCommentConverter,commentToCommentCommandConverter,usersCommandToUsersConverter);
    }

    @Test
    public void getCommentsByUsersAndRecipeOrderByCreatedAtDesc() {
        Comment comment = new Comment();
        comment.setId(1L);
        List<Comment> commentList = new ArrayList<>();
        commentList.add(comment);


        when(commentRepository.findCommentsByRecipeOrderByCreatedAtDesc(any(Recipe.class))).thenReturn(commentList);

        List<Comment> commentListResult = commentService.getCommentsByRecipeOrderByCreatedAtDesc(any(Recipe.class));

        assertEquals(commentList.size(),1);
        verify(commentRepository,times(1)).findCommentsByRecipeOrderByCreatedAtDesc(any());


    }


}