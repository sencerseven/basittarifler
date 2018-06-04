package com.sencerseven.basittarifler.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"recipe","ingredientDetails"})
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToOne
    private Recipe recipe;

    @OneToMany(mappedBy = "ingredient",cascade = CascadeType.ALL)
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
