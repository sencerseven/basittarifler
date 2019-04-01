package com.sencerseven.basittarifler.service;

import com.sencerseven.basittarifler.command.CommentCommand;
import com.sencerseven.basittarifler.command.UsersCommand;
import com.sencerseven.basittarifler.domain.Comment;
import com.sencerseven.basittarifler.domain.Recipe;

import java.util.List;

public interface CommentService {

    List<Comment> getCommentsByRecipeOrderByCreatedAtDesc(Recipe recipe);
    List<Comment> getCommentsByRecipeOrderByCreatedAtAsc(Recipe recipe);

    CommentCommand saveCommentCommand(CommentCommand commentCommand,Recipe recipe,UsersCommand usersCommand);

}
