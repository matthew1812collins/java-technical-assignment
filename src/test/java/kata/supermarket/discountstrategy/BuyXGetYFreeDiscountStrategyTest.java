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

import static org.junit.jupiter.api.Assertions.assertEquals;

class BuyXGetYFreeDiscountStrategyTest {

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
                aSingleItemWithBuy1Get1Free(),
                twoSameItemsWithBuy1Get1Free(),
                twoMixedItemsWithBuy1Get1Free(),
                threeSameItemsBuy1Get2Free()
        );
    }

    private static Arguments noItems() {
        DiscountStrategy discountStrategy = new BuyXGetYFreeDiscountStrategy("DAI-01", 1, 1);
        return Arguments.of("no items", "0.00", Collections.emptyList(), discountStrategy);
    }

    private static Arguments aSingleItemWithBuy1Get1Free() {
        DiscountStrategy discountStrategy = new BuyXGetYFreeDiscountStrategy("DAI-01", 1, 1);
        return Arguments.of("a single item with buy 1 get 1 free", "0.00", Collections.singletonList(aPintOfMilk()), discountStrategy);
    }

    private static Arguments twoSameItemsWithBuy1Get1Free() {
        DiscountStrategy discountStrategy = new BuyXGetYFreeDiscountStrategy("DAI-01", 1, 1);
        return Arguments.of("two same items with buy 1 get 1 free", "0.49", Arrays.asList(aPintOfMilk(), aPintOfMilk()), discountStrategy);
    }

    private static Arguments twoMixedItemsWithBuy1Get1Free() {
        DiscountStrategy discountStrategy = new BuyXGetYFreeDiscountStrategy("DAI-01", 1, 1);
        return Arguments.of("two mixed items with buy 1 get 1 free", "0.00", Arrays.asList(aPintOfMilk(), aPackOfDigestives()), discountStrategy);
    }

    private static Arguments threeSameItemsBuy1Get2Free() {
        DiscountStrategy discountStrategy = new BuyXGetYFreeDiscountStrategy("DAI-01", 1, 2);
        return Arguments.of("three same items with buy 1 get 2 free", "0.98", Arrays.asList(aPintOfMilk(), aPintOfMilk(), aPintOfMilk()), discountStrategy);
    }

    private static Item aPintOfMilk() {
        return new ProductByUnit(new BigDecimal("0.49"), "DAI-01", ProductCategory.DAIRY).oneOf();
    }

    private static Item aPackOfDigestives() {
        return new ProductByUnit(new BigDecimal("1.55"), "BIS-01", ProductCategory.BISCUITS).oneOf();
    }

}