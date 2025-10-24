import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ParametrizedGetPriceTest {

    private final int ingredientCount;
    private final float bunPrice;
    private final float[] ingredientPrices;
    private final float expectedPrice;

    public ParametrizedGetPriceTest(int ingredientCount, float bunPrice, float[] ingredientPrices, float expectedPrice) {
        this.ingredientCount = ingredientCount;
        this.bunPrice = bunPrice;
        this.ingredientPrices = ingredientPrices;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getPrice() {
        return Arrays.asList(new Object[][]{
                {0, 100f, new float[]{}, 200f},
                {1, 120f, new float[]{60f}, 300f},
                {2, 120f, new float[]{60f, 40f}, 340f},
                {3, 150f, new float[]{60f, 40f, 45f}, 445f}
        });
    }

    @Test
    public void testGetPrice() {
        Burger burger = new Burger();

        Bun bun = Mockito.mock(Bun.class);
        Mockito.when(bun.getPrice()).thenReturn(bunPrice);
        burger.setBuns(bun);

        for (int i = 0; i < ingredientCount; i++) {
            Ingredient ingredient = Mockito.mock(Ingredient.class);
            Mockito.when(ingredient.getPrice()).thenReturn(ingredientPrices[i]);
            burger.addIngredient(ingredient);
        }

        float actualPrice = burger.getPrice();
        assertEquals(expectedPrice, actualPrice, 0.001f);
    }
}