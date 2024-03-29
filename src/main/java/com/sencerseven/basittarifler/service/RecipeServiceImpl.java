package com.sencerseven.basittarifler.service;

import com.sencerseven.basittarifler.command.RecipeCommand;
import com.sencerseven.basittarifler.command.UsersCommand;
import com.sencerseven.basittarifler.converter.RecipeCommandToRecipeConverter;
import com.sencerseven.basittarifler.converter.RecipeToRecipeCommandConverter;
import com.sencerseven.basittarifler.converter.UsersCommandToUsersConverter;
import com.sencerseven.basittarifler.domain.Category;
import com.sencerseven.basittarifler.domain.Recipe;
import com.sencerseven.basittarifler.domain.Users;
import com.sencerseven.basittarifler.exceptions.NotFoundException;
import com.sencerseven.basittarifler.repository.RecipeRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService {

    RecipeRepository recipeRepository;

    CategoryService categoryService;

    RecipeToRecipeCommandConverter recipeToRecipeCommandConverter;

    RecipeCommandToRecipeConverter recipeCommandToRecipeConverter;

    UsersCommandToUsersConverter usersCommandToUsersConverter;

    public RecipeServiceImpl(RecipeRepository recipeRepository, CategoryService categoryService, RecipeToRecipeCommandConverter recipeToRecipeCommandConverter, RecipeCommandToRecipeConverter recipeCommandToRecipeConverter, UsersCommandToUsersConverter usersCommandToUsersConverter) {
        this.recipeRepository = recipeRepository;
        this.categoryService = categoryService;
        this.recipeToRecipeCommandConverter = recipeToRecipeCommandConverter;
        this.recipeCommandToRecipeConverter = recipeCommandToRecipeConverter;
        this.usersCommandToUsersConverter = usersCommandToUsersConverter;
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @Override
    public List<Recipe> getLimitRecipes(Pageable pageable) {
     return recipeRepository.findRecipesBy(pageable);
    }

    @Override
    public Recipe getFindById(Long id){
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);
        if(!recipeOptional.isPresent())
            throw new NotFoundException("Recipe is Not Found!");

        return recipeOptional.get();
    }

    @Override
    public List<Recipe> getRecipeOrderByViewCountAndLimit(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page,size, Sort.Direction.DESC,"viewCount");
        return getLimitRecipes(pageRequest);
    }

    @Override
    public List<Recipe> getRecipesByUsers(int page, int size,@Nullable Users user) {
        List<Recipe> recipeList = recipeRepository.findRecipesByUsers(PageRequest.of(page,size),user);
        return recipeList;
    }

    @Override
    public RecipeCommand getCommandFindById(Long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);
        if(!recipeOptional.isPresent())
            throw new NotFoundException("Recipe is Not Found!");

        return recipeToRecipeCommandConverter.convert(recipeOptional.get());
    }

    @Override
    public List<Recipe> getRecipeIdNotPopulerRecipe(int page,int size, Long id) {
        return recipeRepository.findRecipeByIdNot(PageRequest.of(page,size,Sort.Direction.DESC,"recipeStats.totalView"),id);
    }

    @Cacheable(value = "getAllPopulerRecipe")
    @Override
    public List<Recipe> getAllPopulerRecipe(int page,int size) {
        return recipeRepository.findAllByOrderByViewCountDesc(PageRequest.of(page,size));
    }

    @Override
    public Recipe updateByUserCommand(Recipe recipe, UsersCommand usersCommand) {

        if(usersCommand == null || usersCommand.getId() == recipe.getUsers().getId())
            return null;

        recipe.setViewCount(recipe.getViewCount() + 1);


        return recipeRepository.save(recipe);
    }
    //@Cacheable(value = "findAllByOrderByCreatedAtDesc")
    //@Transactional
    @Override
    public Page<Recipe> findAllByOrderByCreatedAtDesc(int page, int size) {
        PageRequest request = PageRequest.of(page,size);
        Page<Recipe> recipePage = recipeRepository.findAllByOrderByCreatedAtDesc(request);
        return recipePage;
    }

    @Override
    public Long countAllBy(){
        return recipeRepository.countAllBy();
    }

    @Override
    public Page<Recipe> findRecipeByCategoriesInOrderByCreatedAtDesc(int page, int size, Set<Category> categories) {

        return recipeRepository.findRecipeByCategoriesInOrderByCreatedAtDesc(PageRequest.of(page,size),categories);
    }

    @Override
    public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand,UsersCommand usersCommand) {
        if(recipeCommand == null || usersCommand == null)
            return null;

        Users detachUser = usersCommandToUsersConverter.convert(usersCommand);
        if(detachUser == null)
            return null;

        Recipe recipe = recipeCommandToRecipeConverter.convert(recipeCommand);
        recipe.addUsers(detachUser);

        Optional<Recipe> save = Optional.of(recipeRepository.save(recipe));

        if(save.isPresent())
            return recipeToRecipeCommandConverter.convert(save.get());
        return null;
    }

    @Override
    public Page<Recipe> findRecipeByTagsContaining(int page,int size,String tags) {
        if(tags == null || tags.equals(""))
            return null;

        return recipeRepository.findRecipesByTags_TagsNameContaining(PageRequest.of(page,size),tags);
    }
}
