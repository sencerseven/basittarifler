package com.sencerseven.basittarifler.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class CategoryCommand {

    private Long id;


    @NotBlank(message = "Lütfen boş bırakmayınız.")
    private String categoryName;


    @NotBlank(message = "Lütfen boş bırakmayınız.")
    private String categoryDescription;

    private CategoryCommand parentCategory;

    private boolean menuActive;

}
