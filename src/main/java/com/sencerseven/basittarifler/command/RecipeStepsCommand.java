package com.sencerseven.basittarifler.command;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeStepsCommand {

    private Long id;

    private String description;

    private String imageURL;

    private int viewRows;

    public RecipeStepsCommand() {
        super();
    }
}
