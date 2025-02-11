package ismgroup58.addProduct.controllers;

import java.util.ArrayList;
import java.util.List;

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
    
    // private final ProductService ps;

    // public AddProductController(ProductService ps) {
    //     this.ps = ps;
    // }

    @GetMapping("")
    public String showMyProductsPage(HttpSession session, Model model) {
        
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
        model.addAttribute("success", success);

        // get list of products
        //List<Product> myproducts_before = new ArrayList<Product>();
        List<Product> myproducts = null;
        // try {
        //     ProductService ps = new ProductService();
        //     myproducts_before = ps.viewMyProducts(user.getUsername());
            
        //     /* Sorting */
        //     String sort = request.getParameter("sort");
        //     if (sort == null) {
        //         sort = "default";
        //     }
        //     myproducts = ps.sortProducts(myproducts_before, sort);
        
        // } catch (Exception e) {
        //     throw new Exception(e.getMessage());
        // }
        model.addAttribute("myproducts", myproducts);

        
        return "my-products.html";
    }

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
                                    Model model) {
        return "redirect:/my-products";
    }
}
