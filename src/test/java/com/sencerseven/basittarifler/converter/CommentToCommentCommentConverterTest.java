package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.CommentCommand;
import com.sencerseven.basittarifler.domain.Comment;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;

public class CommentToCommentCommentConverterTest {

    CommentToCommentCommandConverter commentToCommentCommandConverter;

    @Mock
    UsersToUsersCommandConverter usersToUsersCommandConverter;

    @Mock
    RecipeToRecipeCommandConverter recipeToRecipeCommandConverter;



    private static final Long ID = 1L;


    @Before
    public void setUp() throws Exception {
        commentToCommentCommandConverter = new CommentToCommentCommandConverter(recipeToRecipeCommandConverter,usersToUsersCommandConverter);
    }

    @Test
    public void nullTest(){
        commentToCommentCommandConverter.convert(null);
    }
    @Test
    public void emptyTest(){
        commentToCommentCommandConverter.convert(new Comment());
    }


    @Test
    public void convert() {
        Comment comment = new Comment();
        comment.setId(ID);

        CommentCommand commentCommand = commentToCommentCommandConverter.convert(comment);

        assertEquals(ID,commentCommand.getId());

    }
}