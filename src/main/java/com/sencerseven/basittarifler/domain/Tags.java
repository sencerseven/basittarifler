package com.sencerseven.basittarifler.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
@Table(name = "TAGS")
public class Tags {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String tagsName;

    @OneToOne(optional = false)
    @JoinColumn(name = "recipe_id")
    Recipe recipe;

    public Tags() {
    }

    public Tags(String tags) {
        this.tagsName = tags;
    }
}
