package com.github.udanton.demorecipebook.repositories;

import com.github.udanton.demorecipebook.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
