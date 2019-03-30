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
        recipe.setTitle("Ispanak Borani");
        recipe.setDescription("Ispanak Borani Tarifi");
        recipe.setRecipeText("Mükemmel Ispanaklı Borani ");
        recipe.setViewCount(40);


        recipe.getCategories().add(categoryOptional.get());


        /*Ingredient*/
        Ingredient ingredient = new Ingredient("Borani tarifi");
        Ingredient ingredient1 = new Ingredient("Üzeri için");





        ingredient.addIngredientDetails(new IngredientDetails("2 Demet Ispanak"));
        ingredient.addIngredientDetails(new IngredientDetails("1 Adet Kuru Soğan"));
        ingredient.addIngredientDetails(new IngredientDetails("2 Diş Sarımsak"));
        ingredient.addIngredientDetails(new IngredientDetails("5 Kaşık Süzme Yoğurt"));
        ingredient.addIngredientDetails(new IngredientDetails("1 Çay Kaşığı Karabiber"));
        ingredient.addIngredientDetails(new IngredientDetails("3 Yemek Kaşığı Sıvı Yağ"));
        ingredient.addIngredientDetails(new IngredientDetails("Tuz"));

        ingredient1.addIngredientDetails(new IngredientDetails("1 Çay Bardağı Ceviz"));
        ingredient1.addIngredientDetails(new IngredientDetails("2 Yemek Kaşığı Tereyağ"));
        ingredient1.addIngredientDetails(new IngredientDetails("1 Tatlı Kaşığı Pul Biber"));



        recipe.addIngredient(ingredient);
        recipe.addIngredient(ingredient1);

        recipe.addRecipeImages(new RecipeImages("recipe/ispanak-borani.jpg","ispanak-borani"));
        recipe.addRecipeImages(new RecipeImages("recipe/ispanakli-borani-2.jpg","ispanak-borani"));


        Nutrition nutrition = new Nutrition();
        nutrition.setCarbonhydrate("4.22gr");
        nutrition.setCholesterol("8.95 mlg");
        nutrition.setSaturatedFat("1.52gr");
        nutrition.setFat("7.15gr");
        nutrition.setProtine("4.23gr");
        nutrition.setEnergy("638.31kcal");
        nutrition.setSugar("0.95 gr");
        nutrition.setFiber("1.47 gr");

        recipe.addNutrition(nutrition);


        recipe.addRecipeSteps(new RecipeSteps("Tüm malzemelerimiz hazırsa ıspanaklarımızı bol suda bir kaç kez yıkayıp kumundan arındıralım.","recipe/ispanak-yikama.jpg",1));
        recipe.addRecipeSteps(new RecipeSteps("Tenceremize yağımızı koyalım ısındıktan sonra küp küp doğradığımız soğanlarımızı ve sarımsağımızı ekleyip, kavuralım","recipe/sogan-kavurmak.jpg",2));
        recipe.addRecipeSteps(new RecipeSteps("Üzerine doğradığımız ıspanaklarımızı, karabiber ve tuzumuzu ekleyip kavuralım.","recipe/ispanak-kavurmak.jpg",3));
        recipe.addRecipeSteps(new RecipeSteps("Ispanaklarımız sönüp piştikten sonra servis tabağımıza alıp soğumasını bekleyelim, yoğurdumuzu da ekleyip, karıştıralım.","recipe/yogurtlu-ispanak.png",4));
        recipe.addRecipeSteps(new RecipeSteps("Sos tavamıza tereyağımızı eritip pul biberimizi ekledikten sonra yakmadan kızdıralım","recipe/terayag-eritmek.jpg",5));
        recipe.addRecipeSteps(new RecipeSteps("Cevizlerimizi de bir tavada hafifçe kavuralım.","recipe/fistik-kavurma.jpg",6));
        recipe.addRecipeSteps(new RecipeSteps("Cevizlerimizi ve tereyağlı sosumuzu ıspanak boranimizin üzerine dökelim.\n" +
                "Ispanak Boranimiz masa da ki yerini almaya hazır, afiyet olsun.","recipe/ispanak-borani.jpg",7));



        recipe.addRecipeTips(new RecipeTips("Ispanağı yıkadıktan sonra kurutma işlemi uygulamayın"));
        recipe.addRecipeTips(new RecipeTips("Cevizleri altın sarısı rengini alana kadar kavurun"));

        Users users = userRepository.findByUserName("sencer").get();

        recipe.addRecipeComment(new Comment("voaw çok güzel olmus",users));

        recipe.setUsers(users);

        List<Recipe> recipes = new ArrayList<>();

        recipes.add(recipe);




        return recipes;
    }



}
