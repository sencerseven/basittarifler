package com.sencerseven.basittarifler.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"recipe","ingredientDetails"})
@Entity
public class Ingredient implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToOne
    @JoinColumn(name = "RECIPE_ID")
    private Recipe recipe;

    @OneToMany(mappedBy = "ingredient",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<IngredientDetails> ingredientDetails = new HashSet<>();


    public Ingredient() {
    }

    public Ingredient(String description) {
        this.description = description;
    }

    public Ingredient addIngredientDetails(IngredientDetails ingredientDetails){
        ingredientDetails.setIngredient(this);
        this.ingredientDetails.add(ingredientDetails);
        return this;
    }
}
