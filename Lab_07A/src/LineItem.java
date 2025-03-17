public class LineItem {
    private Product product;
    private int productUnit;

    public LineItem(Product product, int productUnit) {
        this.product = product;
        this.productUnit = productUnit;
    }

    public Double getItemTotal() {
        return product.getProductPrice() * productUnit;
    }

    @Override
    public String toString() {
        return product.getName() + " - Quantity: " + productUnit + " - Total: $" + getItemTotal();
    }

}

