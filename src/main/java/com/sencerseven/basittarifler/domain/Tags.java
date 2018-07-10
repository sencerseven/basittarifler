package com.sencerseven.basittarifler.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Tags {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(unique = true)
    private String tags;

    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE})
    @JoinTable(name = "TAGS_RECIPE"
        ,joinColumns = @JoinColumn(name = "tags_id")
        ,inverseJoinColumns = @JoinColumn(name = "recipe_id"))
    Set<Recipe> recipes = new HashSet<>();

    public Tags() {
    }

    public Tags(String tags) {
        this.tags = tags;
    }
}
