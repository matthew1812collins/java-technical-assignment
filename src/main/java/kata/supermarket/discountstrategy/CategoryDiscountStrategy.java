package kata.supermarket.discountstrategy;

import kata.supermarket.item.Item;
import kata.supermarket.product.ProductCategory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryDiscountStrategy implements DiscountStrategy {

    private final ProductCategory productCategoryToDiscountOn;
    private final double percentDiscount;

    public CategoryDiscountStrategy(ProductCategory productCategoryToDiscountOn, double percentDiscount) {
        this.productCategoryToDiscountOn = productCategoryToDiscountOn;

        if (percentDiscount < 0) {
            percentDiscount = 0;
        } else if(percentDiscount > 100) {
            percentDiscount = 100;
        }

        this.percentDiscount = percentDiscount;
    }

    @Override
    public BigDecimal calculateDiscount(List<Item> items) {
        List<Item> discountItems = items.stream()
                .filter(item -> item.getProductCategory().equals(productCategoryToDiscountOn))
                .collect(Collectors.toList());

        BigDecimal discountItemsTotal = discountItems.stream().map(Item::getPrice)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO)
                .setScale(2, RoundingMode.HALF_UP);

        return discountItemsTotal.multiply(new BigDecimal(percentDiscount / 100)).setScale(2, RoundingMode.HALF_UP);
    }
}
