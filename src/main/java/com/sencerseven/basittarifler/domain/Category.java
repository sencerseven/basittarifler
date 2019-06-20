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
@EqualsAndHashCode(exclude = {"recipes", "categories", "parentCategory", "childrenCategory"})
@Entity
@Table(name = "CATEGORY")
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoryName;

    private String categoryDescription;

    private String categoryUrl;

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private Category parentCategory;

    @OneToMany(mappedBy = "parentCategory", cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.REMOVE},
            orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Category> childrenCategory = new HashSet<>();

    private boolean menuActive;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinTable(name = "CAT_RECIPE"
            , joinColumns = @JoinColumn(name = "category_id")
            , inverseJoinColumns = @JoinColumn(name = "recipe_id"))
    Set<Recipe> recipes = new HashSet<>();

    private boolean mainPageStatus;

    public Category addChildren(Category category) {
        this.getChildrenCategory().add(category);
        category.setParentCategory(this);
        return this;
    }

    public Category removeChildren(final Category childCategory) {
        if (childCategory != null)
            childCategory.setParentCategory(null);
        return this;
    }

}
