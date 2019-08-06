package com.github.udanton.demorecipebook.services;

import com.github.udanton.demorecipebook.commands.IngredientCommand;
import com.github.udanton.demorecipebook.converters.IngredientCommandToIngredient;
import com.github.udanton.demorecipebook.converters.IngredientToIngredientCommand;
import com.github.udanton.demorecipebook.converters.UnitOfMeasureCommandToUnitOfMeasure;
import com.github.udanton.demorecipebook.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.github.udanton.demorecipebook.domain.Ingredient;
import com.github.udanton.demorecipebook.domain.Recipe;
import com.github.udanton.demorecipebook.repositories.RecipeRepository;
import com.github.udanton.demorecipebook.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class IngredientServiceImplTest {

    private IngredientServiceImpl ingredientService;

    private IngredientToIngredientCommand ingredientToIngredientCommand;
    private IngredientCommandToIngredient ingredientCommandToIngredient;


    @Mock
    private RecipeRepository recipeRepository;


    @Mock
    private UnitOfMeasureRepository unitOfMeasureRepository;


    public IngredientServiceImplTest() {
        this.ingredientToIngredientCommand =
                new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        this.ingredientCommandToIngredient =
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ingredientService = new IngredientServiceImpl(ingredientToIngredientCommand, ingredientCommandToIngredient,
                recipeRepository, unitOfMeasureRepository);
    }

    @Test
    public void findByRecipeIdAndIngredientId() {
        Recipe recipe = new Recipe();

        String id1 = UUID.randomUUID().toString();
        String id2 = UUID.randomUUID().toString();
        String id3 = UUID.randomUUID().toString();
        String id4 = UUID.randomUUID().toString();

        recipe.setId(id1);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(id2);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(id3);

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(id4);

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);

        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);

        IngredientCommand ingredientCommand =
                ingredientService.findByRecipeIdAndIngredientId(id1, id3);

        assertEquals(id3, ingredientCommand.getId());
        assertEquals(id1, ingredientCommand.getRecipeId());
        verify(recipeRepository, times(1)).findById(anyString());
    }

    @Test
    public void saveRecipeCommand() {
        IngredientCommand command = new IngredientCommand();

        String id1 = UUID.randomUUID().toString();
        String id2 = UUID.randomUUID().toString();
        String id3 = UUID.randomUUID().toString();

        command.setId(id3);
        command.setRecipeId(id2);

        Optional<Recipe> recipeOptional = Optional.of(new Recipe());

        Recipe savedRecipe = new Recipe();
        savedRecipe.addIngredient(new Ingredient());
        savedRecipe.getIngredients().iterator().next().setId(id3);

        when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);
        when(recipeRepository.save(any())).thenReturn(savedRecipe);

        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

        assertEquals(id3, savedCommand.getId());
        verify(recipeRepository, times(1)).findById(anyString());
        verify(recipeRepository, times(1)).save(any(Recipe.class));
    }

    @Test
    public void deleteById() {

        String id1 = UUID.randomUUID().toString();

        String id3 = UUID.randomUUID().toString();

        Recipe recipe = new Recipe();
        Ingredient ingredient = new Ingredient();
        ingredient.setId(id3);

        recipe.addIngredient(ingredient);
        ingredient.setRecipe(recipe);

        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);

        ingredientService.deleteById(id1, id3);

        verify(recipeRepository, times(1)).findById(anyString());
        verify(recipeRepository, times(1)).save(any(Recipe.class));
    }
}