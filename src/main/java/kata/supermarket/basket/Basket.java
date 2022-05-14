package kata.supermarket.basket;

import kata.supermarket.discountstrategy.DiscountStrategy;
import kata.supermarket.discountstrategy.RegularPricingStrategy;
import kata.supermarket.item.Item;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Basket {
    private final List<Item> items = new ArrayList<>();
    private DiscountStrategy discountStrategy = new RegularPricingStrategy();

    public void add(final Item item) {
        this.items.add(item);
    }

    List<Item> items() {
        return Collections.unmodifiableList(items);
    }

    public void setDiscountStrategy(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    DiscountStrategy discountStrategy() {
        return discountStrategy;
    }

    public BigDecimal total() {
        return new TotalCalculator().calculate();
    }

    private class TotalCalculator {
        private final List<Item> items;
        private final DiscountStrategy discountStrategy;

        TotalCalculator() {
            this.items = items();
            this.discountStrategy = discountStrategy();
        }

        private BigDecimal subtotal() {
            return items.stream().map(Item::getPrice)
                    .reduce(BigDecimal::add)
                    .orElse(BigDecimal.ZERO)
                    .setScale(2, RoundingMode.HALF_UP);
        }

        /**
         * TODO: This could be a good place to apply the results of
         *  the discount calculations.
         *  It is not likely to be the best place to do those calculations.
         *  Think about how Basket could interact with something
         *  which provides that functionality.
         */
        private BigDecimal discounts() {
            return discountStrategy.calculateDiscount(items);
        }

        private BigDecimal calculate() {
            return subtotal().subtract(discounts());
        }
    }
}
