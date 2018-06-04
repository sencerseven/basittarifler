package com.sencerseven.basittarifler.command;

import com.sencerseven.basittarifler.domain.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {

    private Long id;

    private String recipeTitle;

    private String recipeDescription;

    private String recipeText;

    private Date created_at;

    Set<CategoryCommand> categories = new HashSet<>();
}
