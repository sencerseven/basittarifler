package com.sencerseven.basittarifler.command;


import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class RecipeStepsCommand {

    private Long id;

    private String description;

    private String imgURL;

    private MultipartFile imageFile;

    private int viewRows;

    public RecipeStepsCommand() {
        super();
    }
}
