package com.sencerseven.basittarifler.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class RecipeImagesCommand {

    private Long id;

    private MultipartFile imageFile;

    private String imageUrl;

    private String description;
}
