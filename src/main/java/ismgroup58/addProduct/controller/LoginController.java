package ismgroup58.addProduct.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ismgroup58.addProduct.data.UserService;

@Controller
public class LoginController {

    private UserService us;
    
    @GetMapping("login")
    public String showLoginPage() {
        return "login.html";
    }

    

}
