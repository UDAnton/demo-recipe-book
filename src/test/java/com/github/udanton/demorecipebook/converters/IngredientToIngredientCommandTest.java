package com.github.udanton.demorecipebook.converters;

import com.github.udanton.demorecipebook.commands.IngredientCommand;
import com.github.udanton.demorecipebook.domain.Ingredient;
import com.github.udanton.demorecipebook.domain.Recipe;
import com.github.udanton.demorecipebook.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.Assert.*;

public class IngredientToIngredientCommandTest {
    private static final Recipe RECIPE = new Recipe();
    private static final BigDecimal AMOUNT = new BigDecimal("1");
    private static final String DESCRIPTION = "description";
    private static final String UNIT_ID = UUID.randomUUID().toString();
    private static final String ID_VALUE = UUID.randomUUID().toString();

    private IngredientToIngredientCommand ingredientConverter;

    @Before
    public void setUp() {
        ingredientConverter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    public void testNullConvert() {
        assertNull(ingredientConverter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(ingredientConverter.convert(new Ingredient()));
    }

    @Test
    public void convertWithoutUnit() {
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setRecipe(RECIPE);
        ingredient.setAmount(AMOUNT);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setUnit(null);

        //when
        IngredientCommand ingredientCommand = ingredientConverter.convert(ingredient);

        //then
        assertNull(ingredientCommand.getUnit());
        assertEquals(ID_VALUE, ingredientCommand.getId());
        assertEquals(AMOUNT, ingredientCommand.getAmount());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
    }

    @Test
    public void convertWithUnit() {
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setRecipe(RECIPE);
        ingredient.setAmount(AMOUNT);
        ingredient.setDescription(DESCRIPTION);

        UnitOfMeasure unit = new UnitOfMeasure();
        unit.setId(UNIT_ID);

        ingredient.setUnit(unit);

        //when
        IngredientCommand ingredientCommand = ingredientConverter.convert(ingredient);

        //then
        assertEquals(ID_VALUE, ingredientCommand.getId());
        assertNotNull(ingredientCommand.getUnit());
        assertEquals(UNIT_ID, ingredientCommand.getUnit().getId());
        assertEquals(AMOUNT, ingredientCommand.getAmount());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
    }
}