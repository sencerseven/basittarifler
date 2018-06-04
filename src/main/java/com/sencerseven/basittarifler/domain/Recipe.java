package com.sencerseven.basittarifler.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"ingredient","categories","recipeSteps","users","recipeStats","nutrition"})
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String recipeTitle;

    private String recipeDescription;

    private String recipeText;

    private int viewCount;

    @Temporal(TemporalType.DATE)
    private Date created_at;

    @OneToOne(cascade = CascadeType.ALL)
    Nutrition nutrition;

    @ManyToMany
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

    @OneToOne(cascade = CascadeType.ALL)
    RecipeStats recipeStats;

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

    public Recipe addRecipeStats(RecipeStats recipeStats){
        recipeStats.setRecipe(this);
        this.recipeStats = recipeStats;
        return this;
    }

}
