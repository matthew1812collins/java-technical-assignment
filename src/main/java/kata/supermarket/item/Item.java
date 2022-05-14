package kata.supermarket.item;

import kata.supermarket.product.ProductCategory;

import java.math.BigDecimal;

public interface Item {
    BigDecimal getPrice();

    String getProductCode();

    ProductCategory getProductCategory();
}
