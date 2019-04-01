package com.sencerseven.basittarifler.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(exclude = "recipe")
@Entity
public class Cuisine {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String cuisine;

    @OneToOne
    private Recipe recipe;

    public Cuisine() {
    }

    public Cuisine(String cuisine, Recipe recipe) {
        this.cuisine = cuisine;
        this.recipe = recipe;
    }

}
