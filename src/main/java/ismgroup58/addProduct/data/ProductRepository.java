package ismgroup58.addProduct.data;

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
    // public List<Product> sortProducts(List<Product> products, String sort) {
    // }

    /**
     * Returns a Product object given its id.
     * 
     * @param productId the id of the product
     * @return Product Object
     * @throws Exception if something goes wrong in the DB
     */
    public Product getProductById(int productId) throws Exception {
        String query = "SELECT * FROM product WHERE productID = ?;";
        try {
            return jdbcTemplate.queryForObject(query, new ProductMapper(), productId);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
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

    //maybe "delete" and "edit" functions if everything goes well and there is time
}
