package kata.supermarket.product;

public abstract class Product {

    private String productCode;
    private ProductCategory productCategory;

    public Product(String productCode, ProductCategory productCategory) {
        this.productCode = productCode;
        this.productCategory = productCategory;
    }

    public String getProductCode() {
        return productCode;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }
}
