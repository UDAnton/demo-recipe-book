package com.github.udanton.demorecipebook.bootstrap;

import com.github.udanton.demorecipebook.domain.*;
import com.github.udanton.demorecipebook.repositories.CategoryRepository;
import com.github.udanton.demorecipebook.repositories.RecipeRepository;
import com.github.udanton.demorecipebook.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public DataLoader(RecipeRepository recipeRepository, CategoryRepository categoryRepository,
                      UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }


    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(recipes());
        log.debug("Loading bootstrap data");
    }

    private List<Recipe> recipes() {
        List<Recipe> recipes = new ArrayList<>(2);

        Optional<UnitOfMeasure> unitOfMeasuresTeaspoon = unitOfMeasureRepository.findByDescription("Teaspoon");
        Optional<UnitOfMeasure> unitOfMeasuresTablespoon = unitOfMeasureRepository.findByDescription("Tablespoon");
        Optional<UnitOfMeasure> unitOfMeasuresCup = unitOfMeasureRepository.findByDescription("Cup");
        Optional<UnitOfMeasure> unitOfMeasuresEach = unitOfMeasureRepository.findByDescription("Each");
        Optional<UnitOfMeasure> unitOfMeasuresDash = unitOfMeasureRepository.findByDescription("Dash");
        Optional<UnitOfMeasure> unitOfMeasuresPint = unitOfMeasureRepository.findByDescription("Pint");

        if (!unitOfMeasuresTeaspoon.isPresent()) {
            throw new RuntimeException("Unit of measure not found: Teaspoon");
        }

        if (!unitOfMeasuresTablespoon.isPresent()) {
            throw new RuntimeException("Unit of measure not found: Tablespoon");
        }

        if (!unitOfMeasuresCup.isPresent()) {
            throw new RuntimeException("Unit of measure not found: Cup");
        }

        if (!unitOfMeasuresEach.isPresent()) {
            throw new RuntimeException("Unit of measure not found: Each");
        }

        if (!unitOfMeasuresDash.isPresent()) {
            throw new RuntimeException("Unit of measure not found: Dash");
        }

        if (!unitOfMeasuresPint.isPresent()) {
            throw new RuntimeException("Unit of measure not found: Pint");
        }


        Optional<Category> categoryAmerican = categoryRepository.findByDescription("American");
        Optional<Category> categoryMexican = categoryRepository.findByDescription("Mexican");

        if (!categoryAmerican.isPresent()) {
            throw new RuntimeException("Category not found: American");
        }

        if (!categoryMexican.isPresent()) {
            throw new RuntimeException("Category not found: Mexican");
        }


        Recipe recipeGuac = new Recipe();
        recipeGuac.setDescription("Perfect Guacamole");
        recipeGuac.setPrepTime(10);
        recipeGuac.setCookTime(0);
        recipeGuac.setDifficulty(Difficulty.EASY);
        recipeGuac.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "\n" +
                "\n" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "\n" +
                "\n" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.");

        Notes notesGuac = new Notes();
        notesGuac.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries (see our Strawberry Guacamole).\n" +
                "\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.");

        recipeGuac.setNotes(notesGuac);

        recipeGuac.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), unitOfMeasuresEach.get()));
        recipeGuac.addIngredient(new Ingredient("Kosher salt", new BigDecimal(".5"), unitOfMeasuresTeaspoon.get()));
        recipeGuac.addIngredient(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(2), unitOfMeasuresTablespoon.get()));
        recipeGuac.addIngredient(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), unitOfMeasuresTablespoon.get()));
        recipeGuac.addIngredient(new Ingredient("errano chiles, stems and seeds removed, minced", new BigDecimal(2), unitOfMeasuresEach.get()));
        recipeGuac.addIngredient(new Ingredient("cilantro (leaves and tender stems), finely chopped", new BigDecimal(2), unitOfMeasuresTablespoon.get()));
        recipeGuac.addIngredient(new Ingredient("freshly grated black pepper", new BigDecimal(2), unitOfMeasuresDash.get()));
        recipeGuac.addIngredient(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(".5"), unitOfMeasuresEach.get()));

        recipeGuac.getCategories().add(categoryAmerican.get());
        recipeGuac.getCategories().add(categoryMexican.get());

        recipeGuac.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        recipeGuac.setServings(4);
        recipeGuac.setSource("Simply Recipes Food and Cooking Blog");

        recipes.add(recipeGuac);

        Recipe recipeTacos = new Recipe();
        recipeTacos.setDescription("Spicy Grilled Chicken Tacos");
        recipeTacos.setPrepTime(9);
        recipeTacos.setCookTime(20);
        recipeTacos.setDifficulty(Difficulty.MODERATE);

        recipeTacos.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "Spicy Grilled Chicken Tacos\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");

        Notes notesTacos = new Notes();
        notesTacos.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "\n" +
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "\n" +
                "\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!");

        recipeTacos.setNotes(notesTacos);

        recipeTacos.addIngredient(new Ingredient("ancho chili powder", new BigDecimal(2), unitOfMeasuresTablespoon.get()));
        recipeTacos.addIngredient(new Ingredient("dried oregano", new BigDecimal(1), unitOfMeasuresTeaspoon.get()));
        recipeTacos.addIngredient(new Ingredient("dried cumin", new BigDecimal(1), unitOfMeasuresTeaspoon.get()));
        recipeTacos.addIngredient(new Ingredient("sugar", new BigDecimal(1), unitOfMeasuresTeaspoon.get()));
        recipeTacos.addIngredient(new Ingredient("salt", new BigDecimal(".5"), unitOfMeasuresTeaspoon.get()));
        recipeTacos.addIngredient(new Ingredient("clove garlic, finely chopped", new BigDecimal(1), unitOfMeasuresEach.get()));
        recipeTacos.addIngredient(new Ingredient("finely grated orange zest", new BigDecimal(1), unitOfMeasuresTablespoon.get()));
        recipeTacos.addIngredient(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), unitOfMeasuresTablespoon.get()));
        recipeTacos.addIngredient(new Ingredient("olive oil", new BigDecimal(2), unitOfMeasuresTablespoon.get()));
        recipeTacos.addIngredient(new Ingredient("skinless, boneless chicken thighs", new BigDecimal(4), unitOfMeasuresTablespoon.get()));
        recipeTacos.addIngredient(new Ingredient("small corn tortillas", new BigDecimal(8), unitOfMeasuresEach.get()));
        recipeTacos.addIngredient(new Ingredient("cups packed baby arugula", new BigDecimal(3), unitOfMeasuresCup.get()));
        recipeTacos.addIngredient(new Ingredient("medium ripe avocados, sliced", new BigDecimal(2), unitOfMeasuresEach.get()));
        recipeTacos.addIngredient(new Ingredient("radishes, thinly sliced", new BigDecimal(4), unitOfMeasuresEach.get()));
        recipeTacos.addIngredient(new Ingredient("pint cherry tomatoes, halved", new BigDecimal(".5"), unitOfMeasuresPint.get()));
        recipeTacos.addIngredient(new Ingredient("red onion, thinly sliced", new BigDecimal(".25"), unitOfMeasuresEach.get()));
        recipeTacos.addIngredient(new Ingredient("Roughly chopped cilantro", new BigDecimal(4), unitOfMeasuresEach.get()));
        recipeTacos.addIngredient(new Ingredient("cup sour cream thinned with 1/4 cup milk", new BigDecimal(4), unitOfMeasuresCup.get()));
        recipeTacos.addIngredient(new Ingredient("lime, cut into wedges", new BigDecimal(4), unitOfMeasuresEach.get()));


        recipeTacos.getCategories().add(categoryAmerican.get());
        recipeTacos.getCategories().add(categoryMexican.get());

        recipeTacos.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        recipeTacos.setServings(6);
        recipeTacos.setSource("Simply Recipes Food and Cooking Blog");

        recipes.add(recipeTacos);

        return recipes;
    }

}
