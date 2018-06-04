package com.sencerseven.basittarifler.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(exclude = "recipe")
@Entity
public class RecipeSteps {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private String imageURL;

    private int viewRows;

    @ManyToOne
    private Recipe recipe;

    public RecipeSteps() {
    }

    public RecipeSteps(String description, String imageURL, int viewRows) {
        this.description = description;
        this.imageURL = imageURL;
        this.viewRows = viewRows;
    }
}
