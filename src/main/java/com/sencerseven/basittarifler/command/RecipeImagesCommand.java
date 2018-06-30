package com.sencerseven.basittarifler.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecipeImagesCommand {

    private Long id;

    private String url;

    private String description;
}
