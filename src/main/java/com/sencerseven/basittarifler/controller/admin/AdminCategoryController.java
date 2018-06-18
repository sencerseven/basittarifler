package com.sencerseven.basittarifler.controller.admin;

import com.sencerseven.basittarifler.command.CategoryCommand;
import com.sencerseven.basittarifler.domain.Category;
import com.sencerseven.basittarifler.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("admin")
public class AdminCategoryController {

    CategoryService categoryService;

    public AdminCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(value = "category")
    public String indexAction(Model model){
        Set<Category> categories = categoryService.getCategories();
        model.addAttribute("categories",categories);

        return "admin";
    }

    @GetMapping(value = "category/add")
    public String addCategoryAction(Model model){

        Set<Category> categoryList = categoryService.getCategories();
        CategoryCommand categoryCommand = new CategoryCommand();
        model.addAttribute("categories",categoryList);
        model.addAttribute("categoryCommand",categoryCommand);

        return "admin";
    }

    @PostMapping(value="category/add")
    public String addCategory(@Valid CategoryCommand categoryCommand, BindingResult bindingResult){

        if(bindingResult.hasErrors()){

        }
        categoryService.saveCategoryCommand(categoryCommand);

        System.out.println(categoryCommand.getId());
        return "redirect:/admin";
    }


    @GetMapping(value = "category/delete/{id}")
    public String deleteCategory(@PathVariable Long id) throws Exception{

        categoryService.deleteCategory(id);


        return "redirect:/admin/category";
    }
}
