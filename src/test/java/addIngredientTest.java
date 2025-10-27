import org.junit.Before;
import org.junit.Test;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Database;
import praktikum.Ingredient;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


public class addIngredientTest {

    private Database database;
    private Burger burger;

    @Before
    public void createBurger(){

        database = new Database();
        burger = new Burger();

    }

    @Test
    public void setBunsTestSuccess() {
        List<Bun> buns = database.availableBuns();

        burger.setBuns(buns.get(0));
        assertEquals(buns.get(0), burger.bun);
    }

    @Test
    public void addOneIngredientTestSuccess(){
        List<Ingredient> ingredients = database.availableIngredients();
        burger.addIngredient(ingredients.get(0));
        assertEquals(burger.ingredients.get(0), ingredients.get(0));
    }

    @Test
    public void addMoreThanOneIngredientTestSuccess(){
        List<Ingredient> ingredients = database.availableIngredients();
        List<Integer> addedIngredients = new ArrayList<>(Arrays.asList(1, 3, 4));
        for (Integer i: addedIngredients) {
            burger.addIngredient(ingredients.get(i));
            assertTrue(burger.ingredients.contains(ingredients.get(i)));
        }
    }

    @Test
    public void removeOneIngredientTestSuccess(){
        List<Ingredient> ingredients = database.availableIngredients();
        List<Integer> addedIngredients = new ArrayList<>(Arrays.asList(1, 3, 4));
        for (Integer i: addedIngredients) {
            burger.addIngredient(ingredients.get(i));
        }
        burger.removeIngredient(1);
        assertFalse(burger.ingredients.contains(ingredients.get(addedIngredients.get(1))));
    }

    @Test
    public void removeMoreThanOneIngredientTestSuccess(){
        List<Ingredient> ingredients = database.availableIngredients();
        List<Integer> addedIngredients = new ArrayList<>(Arrays.asList(1, 3, 4));
        for (Integer i: addedIngredients) {
            burger.addIngredient(ingredients.get(i));
        }
        List<Integer> removedIngredients = new ArrayList<>(Arrays.asList(1, 3));
        for (Integer i: removedIngredients) {
            int indexIngredientBurger = burger.ingredients.indexOf(ingredients.get(i));
            burger.removeIngredient(indexIngredientBurger);
        }

        for (Integer i: removedIngredients) {
            assertFalse(burger.ingredients.contains(ingredients.get(i)));
        }
    }

    @Test
    public void moveRightIngredientTestSuccess(){
        List<Ingredient> ingredients = database.availableIngredients();
        List<Integer> addedIngredients = new ArrayList<>(Arrays.asList(1, 3, 4));
        for (Integer i: addedIngredients) {
            burger.addIngredient(ingredients.get(i));
        }
        List<Ingredient> originalIngredients = new ArrayList<>(burger.ingredients);
        burger.moveIngredient(0, 2);
        List<Ingredient> newIngredients = burger.ingredients;
        assertEquals(originalIngredients.get(0), newIngredients.get(2));
    }

    @Test
    public void moveLeftIngredientTestSuccess(){
        List<Ingredient> ingredients = database.availableIngredients();
        List<Integer> addedIngredients = new ArrayList<>(Arrays.asList(1, 3, 4));
        for (Integer i: addedIngredients) {
            burger.addIngredient(ingredients.get(i));
        }
        List<Ingredient> originalIngredients = new ArrayList<>(burger.ingredients);
        burger.moveIngredient(2, 0);
        List<Ingredient> newIngredients = burger.ingredients;
        assertEquals(originalIngredients.get(2), newIngredients.get(0));
    }


}
