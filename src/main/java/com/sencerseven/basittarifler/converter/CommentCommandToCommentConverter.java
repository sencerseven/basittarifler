package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.CommentCommand;
import com.sencerseven.basittarifler.domain.Comment;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CommentCommandToCommentConverter implements Converter<CommentCommand,Comment> {
    UsersCommandToUsersConverter usersCommandToUsersConverter;
    RecipeCommandToRecipeConverter recipeCommandToRecipeConverter;

    public CommentCommandToCommentConverter(UsersCommandToUsersConverter usersCommandToUsersConverter, RecipeCommandToRecipeConverter recipeCommandToRecipeConverter) {
        this.usersCommandToUsersConverter = usersCommandToUsersConverter;
        this.recipeCommandToRecipeConverter = recipeCommandToRecipeConverter;
    }

    @Override
    public Comment convert(CommentCommand commentCommand) {
        if(commentCommand == null)
            return null;

        Comment comment = new Comment();
        comment.setId(commentCommand.getId());
        comment.setText(commentCommand.getText());
        comment.setCreatedAt(new Date());

        if(commentCommand.getRecipeCommand() != null)
        comment.setRecipe(recipeCommandToRecipeConverter.convert(commentCommand.getRecipeCommand()));
        if(commentCommand.getUsersCommand() != null)
        comment.setUsers(usersCommandToUsersConverter.convert(commentCommand.getUsersCommand()));

        return comment;


    }
}
