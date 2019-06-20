package com.sencerseven.basittarifler.domain;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
@Table(name = "RECIPE_IMAGES")
public class RecipeImages {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String url;

    private String description;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    public RecipeImages() {
    }

    public RecipeImages(String url, String description) {
        this.url = url;
        this.description = description;
    }
}
