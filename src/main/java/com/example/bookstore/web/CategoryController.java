package com.example.bookstore.web;

import com.example.bookstore.domain.Category;
import com.example.bookstore.domain.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    // Näytä kategoriat listana
    @GetMapping("/categorylist")
    public String showCategories(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "categorylist";  // Näyttää categorylist.html-sivun
    }

    // Näytä lomake uuden kategorian lisäämiseen
    @GetMapping("/addcategory")
    public String addCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "addcategory";  // Näyttää addcategory.html-sivun
    }

    // Tallennetaan uusi kategoria
    @PostMapping("/savecategory")
    public String saveCategory(Category category) {
        categoryRepository.save(category);
        return "redirect:/categorylist";  // Uudelleenohjataan listaan tallennuksen jälkeen
    }


    @PostMapping("/updatecategory")
    public String updateCategory(Category category) {
        categoryRepository.save(category);
        return "redirect:/categorylist";
    }

    @RequestMapping("/deletecategory/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        categoryRepository.deleteById(id);
        return "redirect:/categorylist";
    }
}
