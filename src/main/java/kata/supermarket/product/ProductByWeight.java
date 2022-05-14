package kata.supermarket.product;

import kata.supermarket.item.Item;
import kata.supermarket.item.ItemByWeight;

import java.math.BigDecimal;

public class ProductByWeight {

    private final BigDecimal pricePerKilo;

    public ProductByWeight(final BigDecimal pricePerKilo) {
        this.pricePerKilo = pricePerKilo;
    }

    public BigDecimal pricePerKilo() {
        return pricePerKilo;
    }

    public Item weighing(final BigDecimal kilos) {
        return new ItemByWeight(this, kilos);
    }
}
