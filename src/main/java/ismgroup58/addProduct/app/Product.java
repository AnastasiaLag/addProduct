package ismgroup58.addProduct.app;

public class Product {
    private int productID;
    private String name;
    private String image;
    private String category;
    private String description;
    private double price;
    private int stock;
    private int rating;
    private String supplier;

    /**
     * Constuctor
     *   
     * @param productID int
     * @param name String
     * @param image String
     * @param category String 
     * @param description String
     * @param price double
     * @param stock int
     * @param rating int
     * @param supplierUsername String
     */
    public Product(int productID, String name,String image, String category, 
            String description, double price, int stock, int rating, String supplierUsername) {
        this.productID = productID;
        this.name = name;
        this.image = image;
        this.category = category;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.rating = rating;
        this.supplier = supplierUsername;
    }

    /* getters */
    public int getProductID() {
        return productID;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public int getRating() {
        return rating;
    }

    public String getSupplier() {
        return this.supplier;
    }
}
