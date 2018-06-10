package com.sencerseven.basittarifler.domain;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"recipes","categories","parentCategory","childrenCategory"})
@Entity
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoryName;

    private String categoryDescription;

    private String categoryUrl;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany (mappedBy = "parentCategory",cascade = CascadeType.ALL)
    private Set<Category> childrenCategory = new HashSet<>();

    @ManyToOne()
    private Category parentCategory;

    private boolean menuActive;

    @ManyToMany(mappedBy = "categories")
    Set<Recipe> recipes = new HashSet<>();

    public Category addChildren(Category category){
        this.getChildrenCategory().add(category);
        category.setParentCategory(this);
        return this;
    }

}
