package com.sencerseven.basittarifler.command;

import com.sencerseven.basittarifler.domain.Recipe;
import com.sencerseven.basittarifler.domain.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

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
