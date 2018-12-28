package com.github.udanton.demorecipebook.services;

import com.github.udanton.demorecipebook.commands.IngredientCommand;

public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
