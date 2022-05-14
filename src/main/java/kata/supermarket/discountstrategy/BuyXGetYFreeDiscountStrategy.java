package kata.supermarket.discountstrategy;

import kata.supermarket.item.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class BuyXGetYFreeDiscountStrategy implements DiscountStrategy {

    private final String productCodeToDiscountOn;
    private final int numberOfItemsToBuy;
    private final int numberOfItemsToGetFree;

    public BuyXGetYFreeDiscountStrategy(String productCodeToDiscountOn, int numberOfItemsToBuy, int numberOfItemsToGetFree) {
        this.productCodeToDiscountOn = productCodeToDiscountOn;
        this.numberOfItemsToBuy = numberOfItemsToBuy;
        this.numberOfItemsToGetFree = numberOfItemsToGetFree;
    }

    @Override
    public BigDecimal calculateDiscount(List<Item> items) {
        List<Item> discountItems = items.stream()
                .filter(item -> item.getProductCode().equalsIgnoreCase(productCodeToDiscountOn))
                .collect(Collectors.toList());

        if(discountItems.isEmpty() || discountItems.size() <= numberOfItemsToBuy) {
            return BigDecimal.ZERO.setScale(2);
        }

        int numberOfFreeItems = numberOfItemsToGetFree * (discountItems.size() / (numberOfItemsToBuy + numberOfItemsToGetFree));
        BigDecimal itemPrice = discountItems.get(0).getPrice();

        return itemPrice.multiply(new BigDecimal(numberOfFreeItems));
    }
}
