package kata.supermarket.item;

import kata.supermarket.product.ProductByWeight;
import kata.supermarket.product.ProductCategory;

import java.math.BigDecimal;

public class ItemByWeight implements Item {

    private final ProductByWeight product;
    private final BigDecimal weightInKilos;

    public ItemByWeight(final ProductByWeight product, final BigDecimal weightInKilos) {
        this.product = product;
        this.weightInKilos = weightInKilos;
    }

    @Override
    public BigDecimal getPrice() {
        return product.pricePerKilo().multiply(weightInKilos).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public String getProductCode() {
        return product.getProductCode();
    }

    @Override
    public ProductCategory getProductCategory() {
        return product.getProductCategory();
    }
}
