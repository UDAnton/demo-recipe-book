package com.github.udanton.demorecipebook.controllers;

import com.github.udanton.demorecipebook.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    private RecipeService recipeService;

    @Autowired
    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/"})
    public String index(Model model) {
        model.addAttribute("recipes", recipeService.recipes());
        return "index";
    }

}
