package com.sencerseven.basittarifler.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(exclude = "recipe")
@Entity
public class Nutrition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String protine;

    private String fiber;

    private String fat;

    private String energy;

    private String saturedFat;

    private String carbonhydrate;

    private String cholesterol;

    private String sodium;

    @OneToOne
    private Recipe recipe;

    public Nutrition() {
    }
}
