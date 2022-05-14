package kata.supermarket.product;

import kata.supermarket.item.Item;
import kata.supermarket.item.ItemByUnit;

import java.math.BigDecimal;

public class ProductByUnit {

    private final BigDecimal pricePerUnit;

    public ProductByUnit(final BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public BigDecimal pricePerUnit() {
        return pricePerUnit;
    }

    public Item oneOf() {
        return new ItemByUnit(this);
    }
}
