package com.sencerseven.basittarifler.domain;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(exclude = "ingredient")
@Entity
@Table(name = "INGREDIENT_DETAILS")
public class IngredientDetails implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToOne
    @JoinColumn(name="ingredient_id")
    Ingredient ingredient;


    public IngredientDetails() {
    }

    public IngredientDetails(String description) {
        this.description = description;
    }
}
