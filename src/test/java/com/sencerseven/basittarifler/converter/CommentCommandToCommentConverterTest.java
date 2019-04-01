package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.CommentCommand;
import com.sencerseven.basittarifler.domain.Comment;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;

public class CommentCommandToCommentConverterTest {

    CommentCommandToCommentConverter commentCommandToCommentConverter;

    @Mock
    UsersCommandToUsersConverter usersCommandToUsersConverter;
    @Mock
    RecipeCommandToRecipeConverter recipeCommandToRecipeConverter;

    private static final Long ID = 1L;


    @Before
    public void setUp() throws Exception {
        commentCommandToCommentConverter = new CommentCommandToCommentConverter(usersCommandToUsersConverter,recipeCommandToRecipeConverter);
    }

    @Test
    public void nullTest(){
        commentCommandToCommentConverter.convert(null);
    }
    @Test
    public void emptyTest(){
        commentCommandToCommentConverter.convert(new CommentCommand());
    }


    @Test
    public void convert() {
        CommentCommand commentCommand = new CommentCommand();
        commentCommand.setId(ID);

        Comment comment = commentCommandToCommentConverter.convert(commentCommand);

        assertEquals(ID,comment.getId());

    }
}