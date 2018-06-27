package com.sencerseven.basittarifler.domain;

import lombok.*;
import org.springframework.cache.annotation.Cacheable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"ingredient","categories","recipeSteps","users","recipeStats","nutrition"})
@Entity
public class Recipe implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String recipeTitle;

    private String recipeDescription;

    private String recipeText;

    private String recipeUrl;

    @Column(columnDefinition = "int default 0")
    private int viewCount;

    @Column(columnDefinition = "int default 0")
    private int person;

    @Column(columnDefinition = "int default 0")
    private int portion;

    @Column(columnDefinition = "int default 0")
    private int prepMin;

    @Column(columnDefinition = "int default 0")
    private int cookMin;


    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @OneToOne(cascade = CascadeType.ALL)
    Nutrition nutrition;

    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE})
    @JoinTable(name = "CAT_RECIPE"
                ,joinColumns = @JoinColumn(name = "recipe_id")
                ,inverseJoinColumns = @JoinColumn(name = "category_id"))
   private Set<Category> categories = new HashSet<>();


    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();


    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "recipe")
    private Set<RecipeSteps> recipeSteps = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "recipe")
    private Set<RecipeTips> recipeTips = new HashSet<>();

    @ManyToOne
    private Users users;

    @OneToMany(mappedBy="recipe",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Comment> comments = new HashSet<>();


    public Recipe addUsers(Users users){
        this.users = users;
        users.getRecipes().add(this);
        return this;
    }

    public Recipe addIngredient(Ingredient ingredient){
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }

    public Recipe addNutrition(Nutrition nutrition){
        nutrition.setRecipe(this);
        this.nutrition = nutrition;
        return this;
    }

    public Recipe addRecipeSteps(RecipeSteps recipeSteps){
        recipeSteps.setRecipe(this);
        this.recipeSteps.add(recipeSteps);
        return this;
    }

    public Recipe addRecipeTips(RecipeTips recipeTips){
        recipeTips.setRecipe(this);
        this.recipeTips.add(recipeTips);
        return this;
    }

    public Recipe addRecipeComment(Comment comment){
        comment.setRecipe(this);
        this.comments.add(comment);
        return this;
    }

}
