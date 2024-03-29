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
public class RecipeTips implements Serializable {
    private static final long serialVersionUID = 2L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToOne
    private Recipe recipe;

    private int viewRows;

    public RecipeTips() {
    }

    public RecipeTips(String description) {
        this.description = description;
    }
}
