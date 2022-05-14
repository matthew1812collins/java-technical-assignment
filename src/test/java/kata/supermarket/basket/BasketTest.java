package kata.supermarket.basket;

import kata.supermarket.discountstrategy.BuyXGetYFreeDiscountStrategy;
import kata.supermarket.discountstrategy.CategoryDiscountStrategy;
import kata.supermarket.discountstrategy.DiscountStrategy;
import kata.supermarket.item.Item;
import kata.supermarket.product.ProductByUnit;
import kata.supermarket.product.ProductByWeight;
import kata.supermarket.product.ProductCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasketTest {

    @DisplayName("basket provides its total value when containing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void basketProvidesTotalValue(String description, String expectedTotal, Iterable<Item> items) {
        final Basket basket = new Basket();
        items.forEach(basket::add);
        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }

    static Stream<Arguments> basketProvidesTotalValue() {
        return Stream.of(
                noItems(),
                aSingleItemPricedPerUnit(),
                multipleItemsPricedPerUnit(),
                aSingleItemPricedByWeight(),
                multipleItemsPricedByWeight()
        );
    }

    private static Arguments multipleItemsWithCategoryDiscountStrategy() {
        return Arguments.of("multiple items priced per unit with category discount strategy",
                new CategoryDiscountStrategy(ProductCategory.DAIRY, 50),
                "0.49", Arrays.asList(aPintOfMilk(), aPintOfMilk()));
    }

    private static Arguments aSingleItemPricedByWeight() {
        return Arguments.of("a single weighed item", "1.25", Collections.singleton(twoFiftyGramsOfAmericanSweets()));
    }

    private static Arguments multipleItemsPricedByWeight() {
        return Arguments.of("multiple weighed items", "1.85",
                Arrays.asList(twoFiftyGramsOfAmericanSweets(), twoHundredGramsOfPickAndMix())
        );
    }

    private static Arguments multipleItemsPricedPerUnit() {
        return Arguments.of("multiple items priced per unit", "2.04",
                Arrays.asList(aPackOfDigestives(), aPintOfMilk()));
    }

    private static Arguments aSingleItemPricedPerUnit() {
        return Arguments.of("a single item priced per unit", "0.49", Collections.singleton(aPintOfMilk()));
    }

    private static Arguments noItems() {
        return Arguments.of("no items", "0.00", Collections.emptyList());
    }

    @DisplayName("basket provides its total value, with discount strategy, when containing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void basketProvidesTotalValueWithDiscountStrategy(String description, DiscountStrategy discountStrategy, String expectedTotal, Iterable<Item> items) {
        final Basket basket = new Basket();
        basket.setDiscountStrategy(discountStrategy);
        items.forEach(basket::add);
        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }

    static Stream<Arguments> basketProvidesTotalValueWithDiscountStrategy() {
        return Stream.of(
                multipleItemsWithBuyXGetYFreeDiscountStrategy(),
                multipleItemsWithCategoryDiscountStrategy()
        );
    }

    private static Arguments multipleItemsWithBuyXGetYFreeDiscountStrategy() {
        return Arguments.of("multiple items priced per unit with buy x get y free discount strategy",
                new BuyXGetYFreeDiscountStrategy("DAI-01", 1, 1),
                "0.49", Arrays.asList(aPintOfMilk(), aPintOfMilk()));
    }

    private static Item aPintOfMilk() {
        return new ProductByUnit(new BigDecimal("0.49"), "DAI-01", ProductCategory.DAIRY).oneOf();
    }

    private static Item aPackOfDigestives() {
        return new ProductByUnit(new BigDecimal("1.55"), "BIS-01", ProductCategory.BISCUITS).oneOf();
    }

    private static ProductByWeight aKiloOfAmericanSweets() {
        return new ProductByWeight(new BigDecimal("4.99"), "SWE-01", ProductCategory.SWEETS);
    }

    private static Item twoFiftyGramsOfAmericanSweets() {
        return aKiloOfAmericanSweets().weighing(new BigDecimal(".25"));
    }

    private static ProductByWeight aKiloOfPickAndMix() {
        return new ProductByWeight(new BigDecimal("2.99"), "SWE-02", ProductCategory.SWEETS);
    }

    private static Item twoHundredGramsOfPickAndMix() {
        return aKiloOfPickAndMix().weighing(new BigDecimal(".2"));
    }
}