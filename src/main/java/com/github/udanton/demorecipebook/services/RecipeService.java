package com.github.udanton.demorecipebook.services;

import com.github.udanton.demorecipebook.commands.RecipeCommand;
import com.github.udanton.demorecipebook.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> recipes();

    Recipe findById(String id);

    void deleteById(String id);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    RecipeCommand findCommandById(String l);
}
