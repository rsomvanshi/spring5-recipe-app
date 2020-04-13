package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public DataLoader(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes(String... args) {
        Recipe recipe1 = new Recipe();
        recipe1.setDescription("Recipe 1");

        Notes notes1 = new Notes();
        notes1.setNotes("This is a note 1");
        recipe1.setNotes(notes1);

        Ingredient ingredient1 = new Ingredient();
        UnitOfMeasure uom1 = unitOfMeasureRepository.findByDescription("Cup").get();
        ingredient1.setAmount(BigDecimal.valueOf(2.5));
        ingredient1.setDescription("Ingredient 1");
        ingredient1.setUnitOfMeasure(uom1);

        recipe1.addIngredient(ingredient1);

        Category category1 = categoryRepository.findByDescription("American").get();
        recipe1.addCategory(category1);

        Recipe recipe2 = new Recipe();
        recipe2.setDescription("Recipe 2");

        Notes notes2 = new Notes();
        notes2.setNotes("This is a note 2");
        recipe2.setNotes(notes2);

        Ingredient ingredient2 = new Ingredient();
        UnitOfMeasure uom2 = unitOfMeasureRepository.findByDescription("Pinch").get();
        ingredient2.setAmount(BigDecimal.valueOf(3.5));
        ingredient2.setDescription("Ingredient 2");
        ingredient2.setUnitOfMeasure(uom2);

        recipe2.addIngredient(ingredient2);

        Category category2 = categoryRepository.findByDescription("Italian").get();
        recipe2.addCategory(category2);

        List<Recipe> recipes = new ArrayList<>(2);
        recipes.add(recipe1);
        recipes.add(recipe2);

        System.out.println("Recipes on startup : " + recipes.size());
        return  recipes;
    }
}
