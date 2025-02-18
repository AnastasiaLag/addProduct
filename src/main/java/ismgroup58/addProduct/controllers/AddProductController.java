package ismgroup58.addProduct.controllers;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ismgroup58.addProduct.app.Product;
import ismgroup58.addProduct.app.User;
import ismgroup58.addProduct.data.ProductService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("my-products")
public class AddProductController {
    
    private final ProductService ps;

    public AddProductController(ProductService ps) {
        this.ps = ps;
    }

    @GetMapping("")
    public String showMyProductsPage(HttpSession session, Model model) { 
                                    //, @RequestParam(value="sort", required=false) String sort
                                    
        // retrieve the logged-in user from the session (or null)
        User user = null;
        if (session.getAttribute("currentuser") != null) {
            user = (User) session.getAttribute("currentuser");
        }

        // redirect to login page if there is no current user in the session
        if (user == null) {
            session.setAttribute("errormessage",
                                "You have to log in to view your products!");
            return "redirect:/login";
        }
        
        // display success or error message for adding a product
        String message = (String) session.getAttribute("message");
        boolean success = false;
        if (message != null) {
            success = message.contains("successfully");
        }
        // if it is a message for success, the attribute "success" will be True
        session.setAttribute("success", success);

        // get list of products
        //List<Product> myproducts_before = new ArrayList<Product>();
        List<Product> myproducts = null;
        try {
            myproducts = ps.viewMyProducts(user.getUsername());
            // sorting
            // if (sort == null) {
            //     sort = "default";
            // }
            // myproducts = ps.sortProducts(myproducts_before, sort);
            // System.out.println(myproducts.get(0));
        
        } catch (Exception e) {
            session.setAttribute("message", e.getMessage());
        }
        model.addAttribute("myproducts", myproducts);
  
        return "my-products.html";
    }

    /********************** ADD PRODUCT **********************/
    @GetMapping("/addProduct")
    public String openAddProductForm() {
        return "addProductForm.html";
    }

    @PostMapping("/addProduct/controller")
    public String addProductController(@RequestParam("myimage") String image,
                                    @RequestParam("category") String category,
                                    @RequestParam("supplier") String supplier,
                                    @RequestParam("name") String name,
                                    @RequestParam("price") Double price,
                                    @RequestParam("stock") int stock,
                                    @RequestParam("description") String description,
                                    HttpSession session) {
        try {
            ps.addProduct(name, image, category, stock, stock, description, supplier);
            session.setAttribute("message", "Product added successfully!");
        } catch (Exception e) {
            session.setAttribute("message", "Something went wrong! Failed to add product!");
        }
        return "redirect:/my-products";
    }

    /******************** DELETE PRODUCT ********************/
    @GetMapping("/delete")
    public String deleteProduct(@RequestParam("productID") int productID, HttpSession session) {
        try {
            ps.deleteProduct(productID);
            session.setAttribute("message", "Product deleted successfully!");
        } catch (Exception e) {
            session.setAttribute("message", "Something went wrong! Failed to delete product!");
        }
        return "redirect:/my-products";
    }

    /********************* EDIT PRODUCT *********************/
    @GetMapping("/edit")
    public String openEditProductForm(@RequestParam("productID") int productID, Model model) {
        Product product = ps.getProductById(productID);
        model.addAttribute("product", product);
        return "editProduct.html";
    }

    @PostMapping("/edit/controller")
    public String editProduct(@RequestParam("name") String name, 
                                @RequestParam("price") double price,
                                @RequestParam("stock") int stock,
                                @RequestParam("description") String description,
                                @RequestParam("productID") int productID,
                                HttpSession session) {
        try {
            ps.updateProductDetails(productID, name, price, stock, description);
            session.setAttribute("message", "Product edited successfully!");
        } catch (Exception e) {
            session.setAttribute("message", "Something went wrong! Editting failed!");
        }
        return "redirect:/my-products";
    }
}
