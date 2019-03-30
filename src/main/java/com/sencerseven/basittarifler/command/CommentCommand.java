package com.sencerseven.basittarifler.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class CommentCommand {

    private Long id;

    @NotEmpty
    private String text;


    private UsersCommand usersCommand;


    private RecipeCommand recipeCommand;
}
