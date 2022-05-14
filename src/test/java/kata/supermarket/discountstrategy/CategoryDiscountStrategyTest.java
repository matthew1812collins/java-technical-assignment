package kata.supermarket.discountstrategy;

import kata.supermarket.item.Item;
import kata.supermarket.product.ProductByUnit;
import kata.supermarket.product.ProductCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CategoryDiscountStrategyTest {

    @DisplayName("discount strategy provides its total discount when containing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void discountStrategyProvidesTotalDiscount(String description, String expectedDiscount,
                                               List<Item> items, DiscountStrategy discountStrategy) {
        assertEquals(new BigDecimal(expectedDiscount), discountStrategy.calculateDiscount(items));
    }

    static Stream<Arguments> discountStrategyProvidesTotalDiscount() {
        return Stream.of(
                noItems(),
                aSingleDairyItemWithDairy50PercentOff(),
                aSingleNonDairyItemWithDairy50PercentOff(),
                mixedItemsWithDairy50PercentOff()
        );
    }

    private static Arguments noItems() {
        DiscountStrategy discountStrategy = new CategoryDiscountStrategy(ProductCategory.DAIRY, 50);
        return Arguments.of("no items", "0.00", Collections.emptyList(), discountStrategy);
    }

    private static Arguments aSingleDairyItemWithDairy50PercentOff() {
        DiscountStrategy discountStrategy = new CategoryDiscountStrategy(ProductCategory.DAIRY, 50);
        return Arguments.of("a single dairy item with dairy 50 percent off", "0.25", Collections.singletonList(aPintOfMilk()), discountStrategy);
    }

    private static Arguments aSingleNonDairyItemWithDairy50PercentOff() {
        DiscountStrategy discountStrategy = new CategoryDiscountStrategy(ProductCategory.DAIRY, 50);
        return Arguments.of("a single non dairy item with dairy 50 percent off", "0.00", Collections.singletonList(aPackOfDigestives()), discountStrategy);
    }

    private static Arguments mixedItemsWithDairy50PercentOff() {
        DiscountStrategy discountStrategy = new CategoryDiscountStrategy(ProductCategory.DAIRY, 50);
        return Arguments.of("mixed items with dairy 50 percent off", "0.49", Arrays.asList(aPintOfMilk(), aPintOfMilk(), aPackOfDigestives()), discountStrategy);
    }

    private static Item aPintOfMilk() {
        return new ProductByUnit(new BigDecimal("0.49"), "DAI-01", ProductCategory.DAIRY).oneOf();
    }

    private static Item aPackOfDigestives() {
        return new ProductByUnit(new BigDecimal("1.55"), "BIS-01", ProductCategory.BISCUITS).oneOf();
    }

}