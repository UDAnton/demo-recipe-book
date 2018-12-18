package com.github.udanton.demorecipebook.services;

import com.github.udanton.demorecipebook.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> recipes();
}
