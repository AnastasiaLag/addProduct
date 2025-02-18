package ismgroup58.addProduct.data;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ismgroup58.addProduct.app.Product;

@Repository
public class ProductRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Returns a list with all the products
     * of a user based on their username.
     * 
     * @param username of the user
     * @return a list of products that belong to the user
     * @throws Exception if something goes wrong in the DB
     */
    public List<Product> viewMyProducts(String username) throws Exception {
        String query = "SELECT * FROM product WHERE supplier = ?;";
        try {
            return jdbcTemplate.query(query, new ProductMapper(), username);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Sorts the products that the user sees based on 
     * a selected sorting type from the drop down list.
     * 
     * @param products List<Product>
     * @param sort String
     * @return List<Product>
     */
    public List<Product> sortProducts(List<Product> products, String sort) {
        if (sort.equals("price")) {
            // Sort by price in ascending order
            Collections.sort(products, Comparator.comparingDouble(Product::getPrice));
        } else if (sort.equals("rating")) {
            // Sort by rating in descending order
            Collections.sort(products, Comparator.comparingInt(Product::getRating).reversed());
        }
        return products;
    }

    /**
     * Returns a Product object given its id.
     * 
     * @param productId the id of the product
     * @return Product Object
     * @throws Exception if something goes wrong in the DB
     */
    public Product getProductById(int productId) {
        String query = "SELECT * FROM product WHERE productID = ?;";
        return jdbcTemplate.queryForObject(query, new ProductMapper(), productId);

    }

    /**
     * This method inserts a new product in the database
     * based on the elements that the supplier fills in a form.
     * 
     * @param name String
     * @param image String
     * @param categoty String
     * @param price double
     * @param stock int
     * @param description String
     * @param supplier String
     * @throws Exception if something goes wrong
     */
    public void addProduct(String name, String image, String category, 
                        double price, int stock, String description,
                        String supplier) throws Exception {

        String query = "INSERT INTO product (productName, image,"
                    + " category, price, stock, description, supplier)" 
                    + " VALUES (?, ?, ?, ?, ?, ?, ?);";
        try {
            jdbcTemplate.update(query, name, image, category, price, stock, description, supplier);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } 
    }

    /**
     * This method deletes a product from the database
     * based on a given product id.
     * 
     * @param productID the id of the product the user wants to delete
     * @throws Exception if something goes wrong
     */
    public void deleteProduct(int productID) throws Exception {
        String query = "DELETE FROM product WHERE productID = ?;";
        try {
            jdbcTemplate.update(query, productID);
        } catch (Exception e) {    
            throw new Exception("Error: " + e.getMessage());
        } 
    }

    /**
     * This method updates the database when the user
     * edits something in the product's details.
     * 
     * @param productID the id of the product the user wants to edit
     * @param name updated name
     * @param price updated price
     * @param stock updated stock
     * @param description updted description
     * @throws Exception if something goes wrong
     */
    public void updateProductDetails(int productID, String name, double price,
                                    int stock, String description) throws Exception {
        String query = "UPDATE product SET productName = ?, price = ?, "
                    + "stock = ?, description = ? WHERE productID = ?;";
        try {
            jdbcTemplate.update(query, name, price, stock, description, productID);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }
}
