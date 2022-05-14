package kata.supermarket.item;

import kata.supermarket.product.ProductByUnit;
import kata.supermarket.product.ProductCategory;

import java.math.BigDecimal;

public class ItemByUnit implements Item {

    private final ProductByUnit productByUnit;

    public ItemByUnit(final ProductByUnit productByUnit) {
        this.productByUnit = productByUnit;
    }

    @Override
    public BigDecimal getPrice() {
        return productByUnit.pricePerUnit();
    }

    @Override
    public String getProductCode() {
        return productByUnit.getProductCode();
    }

    @Override
    public ProductCategory getProductCategory() {
        return productByUnit.getProductCategory();
    }
}
