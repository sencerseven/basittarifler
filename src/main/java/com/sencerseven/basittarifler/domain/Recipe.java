package com.sencerseven.basittarifler.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"recipeTips", "ingredient", "categories", "recipeSteps", "users", "recipeStats", "nutrition", "cuisine", "tags"})
@Entity
public class Recipe extends BasePost implements Serializable {
    private static final long serialVersionUID = 1L;

    private String recipeDescription;

    private String recipeText;

    private String difficulty;

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

    @OneToOne(cascade = CascadeType.ALL)
    Nutrition nutrition;

    @OneToOne(cascade = CascadeType.ALL)
    Cuisine cuisine;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinTable(name = "CAT_RECIPE"
            , joinColumns = @JoinColumn(name = "recipe_id")
            , inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "recipe", orphanRemoval = true)
    private Set<Ingredient> ingredients = new HashSet<>();


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "recipe", orphanRemoval = true)
    private Set<RecipeSteps> recipeSteps = new HashSet<>();


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe", orphanRemoval = true)
    private Set<RecipeTips> recipeTips = new LinkedHashSet<>();

    @ManyToOne
    private Users users;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Comment> comments = new HashSet<>();


    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<RecipeImages> recipeImages = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    private Tags tags;


    public Recipe addCategory(Category category) {
        category.getRecipes().add(this);
        this.categories.add(category);
        return this;

    }

    public Recipe addUsers(Users users) {
        this.users = users;
        users.getRecipes().add(this);
        return this;
    }

    public Recipe addIngredient(Ingredient ingredient) {
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }

    public Recipe addNutrition(Nutrition nutrition) {
        nutrition.setRecipe(this);
        this.nutrition = nutrition;
        return this;
    }

    public Recipe addRecipeSteps(RecipeSteps recipeSteps) {
        if(recipeSteps == null)
            return null;

        recipeSteps.setRecipe(this);
        this.recipeSteps.add(recipeSteps);
        return this;
    }


    public Recipe addRecipeTips(RecipeTips recipeTips) {
        this.recipeTips.add(recipeTips);
        recipeTips.setRecipe(this);
        return this;
    }

    public Recipe addRecipeComment(Comment comment) {
        comment.setRecipe(this);
        this.comments.add(comment);
        return this;
    }

    public Recipe addRecipeImages(RecipeImages recipeImages) {
        recipeImages.setRecipe(this);
        this.recipeImages.add(recipeImages);
        return this;
    }

    public Recipe addCuisine(Cuisine cuisine) {
        cuisine.setRecipe(this);
        this.cuisine = cuisine;
        return this;
    }

    public Recipe addTags(Tags tags) {
        tags.setRecipe(this);
        this.tags = tags;
        return this;
    }

}
