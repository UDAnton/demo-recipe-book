package com.github.udanton.demorecipebook.controllers;

import com.github.udanton.demorecipebook.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController {
    private RecipeService recipeService;

    @Autowired
    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/"})
    public String index(Model model) {
        log.debug("Getting index page");
        model.addAttribute("recipes", recipeService.recipes());
        return "index";
    }

}
