package com.sencerseven.basittarifler.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(exclude = "recipe")
@Entity
@Table(name = "RECIPE_STEPS")
public class RecipeSteps implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length=512)
    private String description;

    private String imageURL;

    private int viewRows;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    public RecipeSteps() {
    }

    public RecipeSteps(String description, String imageURL, int viewRows) {
        this.description = description;
        this.imageURL = imageURL;
        this.viewRows = viewRows;
    }
}
