package kata.supermarket;

import kata.supermarket.product.ProductByUnit;
import kata.supermarket.product.ProductCategory;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductByUnitTest {

    @Test
    void singleItemHasExpectedUnitPriceFromProduct() {
        final BigDecimal price = new BigDecimal("2.49");
        assertEquals(price, new ProductByUnit(price, "UNC-01", ProductCategory.UNCATEGORISED).oneOf().getPrice());
    }
}