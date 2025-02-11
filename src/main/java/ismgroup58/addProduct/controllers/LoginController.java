package ismgroup58.addProduct.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ismgroup58.addProduct.app.User;
//import ismgroup58.addProduct.data.UserService;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    // private UserService us;

    // @Autowired
    // public LoginController(UserService us) {
    //     this.us = us;
    // }
    
    @GetMapping("/login")
    public String joinCartel() {
        return "login.html";
    }

    @PostMapping("/login/controller")
    public String loginController(@RequestParam("login_username") String username,
                                @RequestParam("login_password") String password,
                                Model model, HttpSession session) {
        
        // here will be the authentication (this is for testing)
        session.setAttribute("username", username);
        return "redirect:/my-products";
        // try {
        //     User user = us.authenticate(username, password);
        //     session.addAttribute("currentuser", user);
        //     return "redirect:/my-products";
        // } catch (Exception e) {
        //     model.addAttribute("message", e.getMessage());
        //     return "redirect:/login";
        // }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "logout.html";
    }

}
