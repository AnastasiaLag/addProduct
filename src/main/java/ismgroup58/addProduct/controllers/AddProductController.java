package ismgroup58.addProduct.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("my-products")
public class AddProductController {
    
    @GetMapping("")
    public String showMyProductsPage() {
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
        return "redirect:";
    }
}
