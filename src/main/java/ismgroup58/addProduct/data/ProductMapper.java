package ismgroup58.addProduct.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ismgroup58.addProduct.app.Product;

public class ProductMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rows) throws SQLException {
        return new Product(rs.getInt("productID"),
                        rs.getString("productName"),
                        rs.getString("image"),
                        rs.getString("category"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("stock"),
                        0, // the ratings are calculated through a method, 
                                  // we use another class in the project Review.java,
                                  // I don't think it's is important to show here.
                        rs.getString("supplier")
                        );
    }
    
}
