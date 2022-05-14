package kata.supermarket.discountstrategy;

import kata.supermarket.item.Item;

import java.math.BigDecimal;
import java.util.List;

public interface DiscountStrategy {

    BigDecimal calculateDiscount(List<Item> items);
}
