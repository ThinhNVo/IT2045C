public class Product {
    private String productName;
    private double productPrice;

    public Product(String productName, double productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public String getName() {
        return productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

}
