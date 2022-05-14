package kata.supermarket.discountstrategy;

import kata.supermarket.item.Item;

import java.math.BigDecimal;
import java.util.List;

public class RegularPricingStrategy implements DiscountStrategy {

    @Override
    public BigDecimal calculateDiscount(List<Item> items) {
        return BigDecimal.ZERO;
    }
}