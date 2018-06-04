package com.sencerseven.basittarifler.domain;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class RecipeStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,columnDefinition = "int default 0")
    private Long totalView;


    @OneToOne
    private Recipe recipe;

    public RecipeStats() {
    }

    public RecipeStats(Long totalView, Recipe recipe) {
        this.totalView = totalView;
        this.recipe = recipe;
    }
}
