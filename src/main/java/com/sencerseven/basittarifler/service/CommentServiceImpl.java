package com.sencerseven.basittarifler.service;

import com.sencerseven.basittarifler.command.CommentCommand;
import com.sencerseven.basittarifler.command.UsersCommand;
import com.sencerseven.basittarifler.converter.CommentCommandToCommentConverter;
import com.sencerseven.basittarifler.converter.CommentToCommentCommandConverter;
import com.sencerseven.basittarifler.converter.UsersCommandToUsersConverter;
import com.sencerseven.basittarifler.domain.Comment;
import com.sencerseven.basittarifler.domain.Recipe;
import com.sencerseven.basittarifler.domain.Users;
import com.sencerseven.basittarifler.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    CommentRepository commentRepository;

    CommentCommandToCommentConverter commentCommandToCommentConverter;

    CommentToCommentCommandConverter commentToCommentCommandConverter;

    UsersCommandToUsersConverter usersCommandToUsersConverter;

    public CommentServiceImpl(CommentRepository commentRepository, CommentCommandToCommentConverter commentCommandToCommentConverter, CommentToCommentCommandConverter commentToCommentCommandConverter, UsersCommandToUsersConverter usersCommandToUsersConverter) {
        this.commentRepository = commentRepository;
        this.commentCommandToCommentConverter = commentCommandToCommentConverter;
        this.commentToCommentCommandConverter = commentToCommentCommandConverter;
        this.usersCommandToUsersConverter = usersCommandToUsersConverter;
    }

    @Override
    public List<Comment> getCommentsByRecipeOrderByCreatedAtDesc(Recipe recipe) {
        return commentRepository.findCommentsByRecipeOrderByCreatedAtDesc(recipe);
    }

    @Override
    public List<Comment> getCommentsByRecipeOrderByCreatedAtAsc(Recipe recipe) {
        return commentRepository.findCommentsByRecipeOrderByCreatedAtAsc(recipe);
    }

    @Override
    public CommentCommand saveCommentCommand(CommentCommand commentCommand, Recipe recipe, UsersCommand usersCommand) {

        Comment detachedComment = commentCommandToCommentConverter.convert(commentCommand);
        Users users = usersCommandToUsersConverter.convert(usersCommand);

        detachedComment.setRecipe(recipe);
        detachedComment.setUsers(users);
        Comment commentSaved =  commentRepository.save(detachedComment);

        return commentToCommentCommandConverter.convert(commentSaved);
    }


}
