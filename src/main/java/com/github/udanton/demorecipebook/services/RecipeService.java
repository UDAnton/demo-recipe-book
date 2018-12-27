package com.github.udanton.demorecipebook.services;

import com.github.udanton.demorecipebook.commands.RecipeCommand;
import com.github.udanton.demorecipebook.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> recipes();

    Recipe findById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    RecipeCommand findCommandById(Long l);
}
