package kata.supermarket.item;

import kata.supermarket.product.ProductByUnit;

import java.math.BigDecimal;

public class ItemByUnit implements Item {

    private final ProductByUnit productByUnit;

    public ItemByUnit(final ProductByUnit productByUnit) {
        this.productByUnit = productByUnit;
    }

    public BigDecimal price() {
        return productByUnit.pricePerUnit();
    }
}
