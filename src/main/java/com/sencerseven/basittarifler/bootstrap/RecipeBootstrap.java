package com.sencerseven.basittarifler.bootstrap;

import com.sencerseven.basittarifler.domain.*;
import com.sencerseven.basittarifler.repository.CategoryRepository;
import com.sencerseven.basittarifler.repository.RecipeRepository;
import com.sencerseven.basittarifler.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {


    CategoryRepository categoryRepository;
    RecipeRepository recipeRepository;
    UserRepository userRepository;


    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
        log.debug("Bootstrap Recipe is saved");
    }

    public List<Recipe> getRecipes(){
        Optional<Category> categoryOptional = categoryRepository.findByCategoryDescription("Sıcak Yemekler");

        if(!categoryOptional.isPresent())
            throw  new RuntimeException("No found category !");




        Recipe recipe = new Recipe();
        recipe.setCreated_at(new Date());
        recipe.setRecipeTitle("Spicy Grilled Chicken Taco");
        recipe.setRecipeDescription("Spicy Grilled Chicken Taco");
        recipe.setRecipeText("1 Prepare a gas or charcoal grill for medium-high");
        recipe.setViewCount(3);


        recipe.getCategories().add(categoryOptional.get());


        /*Ingredient*/
        Ingredient ingredient = new Ingredient("Üstü için");
        Ingredient ingredient1 = new Ingredient("Kenarları için");
        Ingredient ingredient2 = new Ingredient("Altı için");





        ingredient.addIngredientDetails(new IngredientDetails("dolmalık biber"));
        ingredient.addIngredientDetails(new IngredientDetails("maydanoz"));
        ingredient.addIngredientDetails(new IngredientDetails("soğan"));
        ingredient1.addIngredientDetails(new IngredientDetails("krema"));
        ingredient1.addIngredientDetails(new IngredientDetails("çikolata"));
        ingredient2.addIngredientDetails(new IngredientDetails("kek"));



        recipe.addIngredient(ingredient);
        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);


        Nutrition nutrition = new Nutrition();
        nutrition.setCarbonhydrate("10gr");
        nutrition.setFat("12gr");
        nutrition.setProtine("15gr");

        recipe.addNutrition(nutrition);


        recipe.addRecipeSteps(new RecipeSteps("Ilık su ve sütü geniş bir karıştırma kabına alın. Yaş maya ve toz şekeri ekleyip karıştırdıktan sonra mayanın aktive olması için 5 dakika kadar bekletin.","",1));
        recipe.addRecipeSteps(new RecipeSteps("Maya tamamen eridikten sonra ayçiçek yağı, yumurta ve tuzu katın. Tüm malzemeyi elinizle karıştırın.","",2));
        recipe.addRecipeSteps(new RecipeSteps("Elenmiş unu, azar azar ekleyip hamuru toparlanana kadar yoğurmaya başlayın.","",3));
        recipe.addRecipeSteps(new RecipeSteps("Yumuşak ve yapışmayacak bir kıvamda bir hamur elde ettiğiniz zaman üzerini nemli bir bezle kapatıp 40-45 dakika kadar oda ısısında dinlendirin.","",4));



        recipe.addRecipeTips(new RecipeTips("Çırpılmış yumurta akına batırdığınız kurabiyelerin üzerini ceviz kırıklarına bulayıp tepsiye dizebilir, bu şekilde cevizli kurabiyeler de hazırlayabilirsiniz."));
        recipe.addRecipeTips(new RecipeTips("Oda sıcaklığında tereyağı kullanmaya özen gösterin.Kurabiye hamuru toparlanıp ele yapışmayacak bir kıvam alana kadar azar azar un eklemeyi sürdürün."));

        RecipeStats recipeStats = new RecipeStats();
        recipeStats.setTotalView(0L);

        recipe.addRecipeStats(recipeStats);


        Users users = userRepository.findByUserName("sencer").get();

        recipe.addRecipeComment(new Comment("voaw çok güzel olmus",users));

        recipe.setUsers(users);

        List<Recipe> recipes = new ArrayList<>(2);

        recipes.add(recipe);


        return recipes;
    }



}
