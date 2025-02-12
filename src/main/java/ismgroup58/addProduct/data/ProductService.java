package ismgroup58.addProduct.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ismgroup58.addProduct.app.Product;

@Service
public class ProductService {
    
    private final ProductRepository pr;

    @Autowired
    public ProductService(ProductRepository pr) {
        this.pr = pr;
    }

    /**
     * @see ismgroup58.addProduct.data.ProductRepository#viewMyProducts(String)
     */
    public List<Product> viewMyProducts(String username) throws Exception {
        return pr.viewMyProducts(username);
    }    

    /**
     * @see ismgroup58.addProduct.data.ProductRepository#getProductById(int)
     */
    public Product getProductById(int productId) throws Exception {
        return pr.getProductById(productId);
    } 

    /**
     * @see ismgroup58.addProduct.data.ProductRepository#addProduct(String, String, String, double, int, String, String)
     */
    public void addProduct(String name, String image, String category, 
                        double price, int stock, String description,
                        String supplier) throws Exception {
        pr.addProduct(name, image, category, price, stock, description, supplier);
    }
}
