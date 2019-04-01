package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.CommentCommand;
import com.sencerseven.basittarifler.domain.Comment;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CommentToCommentCommandConverter implements Converter<Comment,CommentCommand> {

    RecipeToRecipeCommandConverter recipeToRecipeCommandConverter;

    UsersToUsersCommandConverter usersToUsersCommandConverter;

    public CommentToCommentCommandConverter(RecipeToRecipeCommandConverter recipeToRecipeCommandConverter, UsersToUsersCommandConverter usersToUsersCommandConverter) {
        this.recipeToRecipeCommandConverter = recipeToRecipeCommandConverter;
        this.usersToUsersCommandConverter = usersToUsersCommandConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public CommentCommand convert(Comment comment) {
        if(comment == null)
            return null;
        CommentCommand commentCommand = new CommentCommand();
        commentCommand.setId(comment.getId());
        commentCommand.setText(comment.getText());

        if(comment.getRecipe() != null)
            commentCommand.setRecipeCommand(recipeToRecipeCommandConverter.convert(comment.getRecipe()));

        if(comment.getUsers() != null)
            commentCommand.setUsersCommand(usersToUsersCommandConverter.convert(comment.getUsers()));

        return  commentCommand;

    }
}
