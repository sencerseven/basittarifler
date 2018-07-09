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
        categoryRepository.saveAll(getCategory());
        recipeRepository.saveAll(getRecipes());
        recipeRepository.saveAll(getRecipes());
        log.debug("Bootstrap Recipe is saved");
    }

    public List<Category> getCategory(){

        Optional<Category> categoryOptional = categoryRepository.findById(2L);

        if(!categoryOptional.isPresent())
            throw  new RuntimeException("No found category !");

        List<Category> categories = new ArrayList<>();

        Category category = new Category();
        category.setCategoryName("Peynirler");
        category.setCategoryDescription("Peynirler");
        category.setParentCategory(categoryOptional.get());


        Category category2 = new Category();
        category2.setCategoryName("Zeytinler");
        category2.setCategoryDescription("Zeytinler");
        category2.setParentCategory(categoryOptional.get());



        categories.add(category);
        categories.add(category2);

        return categories;
    }



    public List<Recipe> getRecipes(){

        Optional<Category> categoryOptional = categoryRepository.findById(1L);

        if(!categoryOptional.isPresent())
            throw  new RuntimeException("No found category !");

        Recipe recipe = new Recipe();
        recipe.setCreatedAt(new Date());
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


        recipe.addRecipeSteps(new RecipeSteps("Ilık su ve sütü geniş bir karıştırma kabına alın. Yaş maya ve toz şekeri ekleyip karıştırdıktan sonra mayanın aktive olması için 5 dakika kadar bekletin.","zeytinyagi.jpeg",1));
        recipe.addRecipeSteps(new RecipeSteps("Maya tamamen eridikten sonra ayçiçek yağı, yumurta ve tuzu katın. Tüm malzemeyi elinizle karıştırın.","un.jpeg",2));
        recipe.addRecipeSteps(new RecipeSteps("Elenmiş unu, azar azar ekleyip hamuru toparlanana kadar yoğurmaya başlayın.","zeytinyagi.jpeg",3));
        recipe.addRecipeSteps(new RecipeSteps("Yumuşak ve yapışmayacak bir kıvamda bir hamur elde ettiğiniz zaman üzerini nemli bir bezle kapatıp 40-45 dakika kadar oda ısısında dinlendirin.","un.jpeg",4));



        recipe.addRecipeTips(new RecipeTips("Çırpılmış yumurta akına batırdığınız kurabiyelerin üzerini ceviz kırıklarına bulayıp tepsiye dizebilir, bu şekilde cevizli kurabiyeler de hazırlayabilirsiniz."));
        recipe.addRecipeTips(new RecipeTips("Oda sıcaklığında tereyağı kullanmaya özen gösterin.Kurabiye hamuru toparlanıp ele yapışmayacak bir kıvam alana kadar azar azar un eklemeyi sürdürün."));

        Users users = userRepository.findByUserName("sencer").get();

        recipe.addRecipeComment(new Comment("voaw çok güzel olmus",users));

        recipe.setUsers(users);

        List<Recipe> recipes = new ArrayList<>();

        recipes.add(recipe);


        Recipe recipe2 = new Recipe();
        recipe2.setCreatedAt(new Date());
        recipe2.setRecipeTitle("Ve İşte Kısırların Şahı Hatay İçi");
        recipe2.setRecipeDescription("Spicy Grilled Chicken Taco");
        recipe2.setRecipeText("1 Prepare a gas or charcoal grill for medium-high");
        recipe2.setViewCount(3);

        recipe2.getCategories().add(categoryOptional.get());
        recipe2.addNutrition(nutrition);

        recipe2.getIngredients().add(ingredient);
        recipe2.getIngredients().add(ingredient1);
        recipe2.getIngredients().add(ingredient2);

        recipe2.addRecipeSteps(new RecipeSteps("Ilık su ve sütü geniş bir karıştırma kabına alın. Yaş maya ve toz şekeri ekleyip karıştırdıktan sonra mayanın aktive olması için 5 dakika kadar bekletin.","zeytinyagi.jpeg",1));
        recipe2.addRecipeSteps(new RecipeSteps("Maya tamamen eridikten sonra ayçiçek yağı, yumurta ve tuzu katın. Tüm malzemeyi elinizle karıştırın.","un.jpeg",2));
        recipe2.addRecipeSteps(new RecipeSteps("Elenmiş unu, azar azar ekleyip hamuru toparlanana kadar yoğurmaya başlayın.","zeytinyagi.jpeg",3));
        recipe2.addRecipeSteps(new RecipeSteps("Yumuşak ve yapışmayacak bir kıvamda bir hamur elde ettiğiniz zaman üzerini nemli bir bezle kapatıp 40-45 dakika kadar oda ısısında dinlendirin.","un.jpeg",4));

        recipe2.addRecipeTips(new RecipeTips("Çırpılmış yumurta akına batırdığınız kurabiyelerin üzerini ceviz kırıklarına bulayıp tepsiye dizebilir, bu şekilde cevizli kurabiyeler de hazırlayabilirsiniz."));
        recipe2.addRecipeTips(new RecipeTips("Oda sıcaklığında tereyağı kullanmaya özen gösterin.Kurabiye hamuru toparlanıp ele yapışmayacak bir kıvam alana kadar azar azar un eklemeyi sürdürün."));
        recipe2.setUsers(users);


        recipes.add(recipe2);


        Recipe recipe3 = new Recipe();
        recipe3.setCreatedAt(new Date());
        recipe3.setRecipeTitle("Beşamel Soslu Patates Oturtma");
        recipe3.setRecipeDescription("Spicy Grilled Chicken Taco");
        recipe3.setRecipeText("1 Prepare a gas or charcoal grill for medium-high");
        recipe3.setViewCount(3);

        recipe3.getCategories().add(categoryOptional.get());
        recipe3.addNutrition(nutrition);

        recipe3.getIngredients().add(ingredient);
        recipe3.getIngredients().add(ingredient1);
        recipe3.getIngredients().add(ingredient2);


        recipe3.addRecipeSteps(new RecipeSteps("Ilık su ve sütü geniş bir karıştırma kabına alın. Yaş maya ve toz şekeri ekleyip karıştırdıktan sonra mayanın aktive olması için 5 dakika kadar bekletin.","zeytinyagi.jpeg",1));
        recipe3.addRecipeSteps(new RecipeSteps("Maya tamamen eridikten sonra ayçiçek yağı, yumurta ve tuzu katın. Tüm malzemeyi elinizle karıştırın.","un.jpeg",2));
        recipe3.addRecipeSteps(new RecipeSteps("Elenmiş unu, azar azar ekleyip hamuru toparlanana kadar yoğurmaya başlayın.","zeytinyagi.jpeg",3));
        recipe3.addRecipeSteps(new RecipeSteps("Yumuşak ve yapışmayacak bir kıvamda bir hamur elde ettiğiniz zaman üzerini nemli bir bezle kapatıp 40-45 dakika kadar oda ısısında dinlendirin.","un.jpeg",4));

        recipe3.addRecipeTips(new RecipeTips("Çırpılmış yumurta akına batırdığınız kurabiyelerin üzerini ceviz kırıklarına bulayıp tepsiye dizebilir, bu şekilde cevizli kurabiyeler de hazırlayabilirsiniz."));
        recipe3.addRecipeTips(new RecipeTips("Oda sıcaklığında tereyağı kullanmaya özen gösterin.Kurabiye hamuru toparlanıp ele yapışmayacak bir kıvam alana kadar azar azar un eklemeyi sürdürün."));
        recipe3.setUsers(users);


        recipes.add(recipe3);

        Recipe recipe4 = new Recipe();
        recipe4.setCreatedAt(new Date());
        recipe4.setRecipeTitle("Sebzeli Mercimek Çorbası");
        recipe4.setRecipeDescription("Spicy Grilled Chicken Taco");
        recipe4.setRecipeText("1 Prepare a gas or charcoal grill for medium-high");
        recipe4.setViewCount(3);

        recipe4.getCategories().add(categoryOptional.get());
        recipe4.addNutrition(nutrition);


        recipe4.getIngredients().add(ingredient);
        recipe4.getIngredients().add(ingredient1);
        recipe4.getIngredients().add(ingredient2);

        recipe4.addRecipeImages(new RecipeImages("recipe/chair2.jpg","yazi1"));

        recipe4.addRecipeSteps(new RecipeSteps("Ilık su ve sütü geniş bir karıştırma kabına alın. Yaş maya ve toz şekeri ekleyip karıştırdıktan sonra mayanın aktive olması için 5 dakika kadar bekletin.","recipe/pilav-tarifi.jpg",1));
        recipe4.addRecipeSteps(new RecipeSteps("Maya tamamen eridikten sonra ayçiçek yağı, yumurta ve tuzu katın. Tüm malzemeyi elinizle karıştırın.","recipe/pilav-tarifi.jpg",2));
        recipe4.addRecipeSteps(new RecipeSteps("Elenmiş unu, azar azar ekleyip hamuru toparlanana kadar yoğurmaya başlayın.","recipe/pilav-tarifi.jpg",3));
        recipe4.addRecipeSteps(new RecipeSteps("Yumuşak ve yapışmayacak bir kıvamda bir hamur elde ettiğiniz zaman üzerini nemli bir bezle kapatıp 40-45 dakika kadar oda ısısında dinlendirin.","recipe/pilav-tarifi.jpg",4));

        recipe4.addRecipeTips(new RecipeTips("Çırpılmış yumurta akına batırdığınız kurabiyelerin üzerini ceviz kırıklarına bulayıp tepsiye dizebilir, bu şekilde cevizli kurabiyeler de hazırlayabilirsiniz."));
        recipe4.addRecipeTips(new RecipeTips("Oda sıcaklığında tereyağı kullanmaya özen gösterin.Kurabiye hamuru toparlanıp ele yapışmayacak bir kıvam alana kadar azar azar un eklemeyi sürdürün."));
        recipe4.setUsers(users);


        recipes.add(recipe4);

        Recipe recipe5 = new Recipe();
        recipe5.setCreatedAt(new Date());
        recipe5.setRecipeTitle("Tart Kalıbında Muhallebili Kek");
        recipe5.setRecipeDescription("Spicy Grilled Chicken Taco");
        recipe5.setRecipeText("1 Prepare a gas or charcoal grill for medium-high");
        recipe5.setViewCount(3);

        recipe5.getCategories().add(categoryOptional.get());
        recipe5.addNutrition(nutrition);

        recipe5.getIngredients().add(ingredient);
        recipe5.getIngredients().add(ingredient1);
        recipe5.getIngredients().add(ingredient2);


        recipe5.addRecipeSteps(new RecipeSteps("Ilık su ve sütü geniş bir karıştırma kabına alın. Yaş maya ve toz şekeri ekleyip karıştırdıktan sonra mayanın aktive olması için 5 dakika kadar bekletin.","zeytinyagi.jpeg",1));
        recipe5.addRecipeSteps(new RecipeSteps("Maya tamamen eridikten sonra ayçiçek yağı, yumurta ve tuzu katın. Tüm malzemeyi elinizle karıştırın.","un.jpeg",2));
        recipe5.addRecipeSteps(new RecipeSteps("Elenmiş unu, azar azar ekleyip hamuru toparlanana kadar yoğurmaya başlayın.","zeytinyagi.jpeg",3));
        recipe5.addRecipeSteps(new RecipeSteps("Yumuşak ve yapışmayacak bir kıvamda bir hamur elde ettiğiniz zaman üzerini nemli bir bezle kapatıp 40-45 dakika kadar oda ısısında dinlendirin.","un.jpeg",4));

        recipe5.addRecipeTips(new RecipeTips("Çırpılmış yumurta akına batırdığınız kurabiyelerin üzerini ceviz kırıklarına bulayıp tepsiye dizebilir, bu şekilde cevizli kurabiyeler de hazırlayabilirsiniz."));
        recipe5.addRecipeTips(new RecipeTips("Oda sıcaklığında tereyağı kullanmaya özen gösterin.Kurabiye hamuru toparlanıp ele yapışmayacak bir kıvam alana kadar azar azar un eklemeyi sürdürün."));
        recipe5.setUsers(users);


        recipes.add(recipe5);

        Recipe recipe6 = new Recipe();
        recipe6.setCreatedAt(new Date());
        recipe6.setRecipeTitle("Patates Kızartması");
        recipe6.setRecipeDescription("Spicy Grilled Chicken Taco");
        recipe6.setRecipeText("1 Prepare a gas or charcoal grill for medium-high");
        recipe6.setViewCount(3);

        recipe6.getCategories().add(categoryOptional.get());
        recipe6.addNutrition(nutrition);

        recipe6.getIngredients().add(ingredient);
        recipe6.getIngredients().add(ingredient1);
        recipe6.getIngredients().add(ingredient2);

        recipe6.addRecipeSteps(new RecipeSteps("Ilık su ve sütü geniş bir karıştırma kabına alın. Yaş maya ve toz şekeri ekleyip karıştırdıktan sonra mayanın aktive olması için 5 dakika kadar bekletin.","zeytinyagi.jpeg",1));
        recipe6.addRecipeSteps(new RecipeSteps("Maya tamamen eridikten sonra ayçiçek yağı, yumurta ve tuzu katın. Tüm malzemeyi elinizle karıştırın.","un.jpeg",2));
        recipe6.addRecipeSteps(new RecipeSteps("Elenmiş unu, azar azar ekleyip hamuru toparlanana kadar yoğurmaya başlayın.","zeytinyagi.jpeg",3));
        recipe6.addRecipeSteps(new RecipeSteps("Yumuşak ve yapışmayacak bir kıvamda bir hamur elde ettiğiniz zaman üzerini nemli bir bezle kapatıp 40-45 dakika kadar oda ısısında dinlendirin.","un.jpeg",4));

        recipe6.addRecipeTips(new RecipeTips("Çırpılmış yumurta akına batırdığınız kurabiyelerin üzerini ceviz kırıklarına bulayıp tepsiye dizebilir, bu şekilde cevizli kurabiyeler de hazırlayabilirsiniz."));
        recipe6.addRecipeTips(new RecipeTips("Oda sıcaklığında tereyağı kullanmaya özen gösterin.Kurabiye hamuru toparlanıp ele yapışmayacak bir kıvam alana kadar azar azar un eklemeyi sürdürün."));
        recipe6.setUsers(users);


        recipes.add(recipe6);

        return recipes;
    }



}
