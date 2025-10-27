import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ParametrizedGetReceiptTest {

    private final String bunName;
    private final float bunPrice;
    private final IngredientType[] ingredientTypes;
    private final String[] ingredientNames;
    private final float[] ingredientPrices;
    private final String expectedReceipt;

    public ParametrizedGetReceiptTest(String bunName, float bunPrice,
                                      IngredientType[] ingredientTypes, String[] ingredientNames,
                                      float[] ingredientPrices, String expectedReceipt) {
        this.bunName = bunName;
        this.bunPrice = bunPrice;
        this.ingredientTypes = ingredientTypes;
        this.ingredientNames = ingredientNames;
        this.ingredientPrices = ingredientPrices;
        this.expectedReceipt = expectedReceipt;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getReceiptData() {
        return Arrays.asList(new Object[][]{
                {
                        "black bun",
                        100f,
                        new IngredientType[]{},
                        new String[]{},
                        new float[]{},
                        String.format("(==== black bun ====)%n(==== black bun ====)%n%nPrice: 200.000000%n")
                },
                {
                        "white bun",
                        120f,
                        new IngredientType[]{IngredientType.SAUCE},
                        new String[]{"hot sauce"},
                        new float[]{60f},
                        String.format("(==== white bun ====)%n= sauce hot sauce =%n(==== white bun ====)%n%nPrice: 300.000000%n")
                },
                {
                        "red bun",
                        150f,
                        new IngredientType[]{IngredientType.FILLING, IngredientType.SAUCE},
                        new String[]{"cutlet", "ketchup"},
                        new float[]{80f, 40f},
                        String.format("(==== red bun ====)%n= filling cutlet =%n= sauce ketchup =%n(==== red bun ====)%n%nPrice: 420.000000%n")
                },
                {
                        "special bun",
                        200f,
                        new IngredientType[]{IngredientType.FILLING, IngredientType.FILLING, IngredientType.SAUCE},
                        new String[]{"cheese", "tomato", "mayo"},
                        new float[]{50f, 30f, 20f},
                        String.format("(==== special bun ====)%n= filling cheese =%n= filling tomato =%n= sauce mayo =%n(==== special bun ====)%n%nPrice: 500.000000%n")
                }
        });
    }

    @Test
    public void testGetReceipt() {
        Burger burger = new Burger();
        Bun bun = Mockito.mock(Bun.class);
        Mockito.when(bun.getName()).thenReturn(bunName);
        Mockito.when(bun.getPrice()).thenReturn(bunPrice);
        burger.setBuns(bun);
        for (int i = 0; i < ingredientTypes.length; i++) {
            Ingredient ingredient = Mockito.mock(Ingredient.class);
            Mockito.when(ingredient.getType()).thenReturn(ingredientTypes[i]);
            Mockito.when(ingredient.getName()).thenReturn(ingredientNames[i]);
            Mockito.when(ingredient.getPrice()).thenReturn(ingredientPrices[i]);
            burger.addIngredient(ingredient);
        }
        String actualReceipt = burger.getReceipt();
        assertEquals(expectedReceipt, actualReceipt);
    }
}