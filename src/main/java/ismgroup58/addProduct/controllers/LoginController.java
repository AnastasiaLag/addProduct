package ismgroup58.addProduct.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ismgroup58.addProduct.app.User;
import ismgroup58.addProduct.data.UserService;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    private UserService us;

    @Autowired
    public LoginController(UserService us) {
        this.us = us;
    }
    
    @GetMapping("/login")
    public String joinCartel() {
        return "login.html";
    }

    @PostMapping("/login/controller")
    public String loginController(@RequestParam("login_username") String username,
                                @RequestParam("login_password") String password,
                                HttpSession session) {
        
        // here will be the authentication of the user
        try {
            User user = us.authenticate(username, password);
            session.setAttribute("currentuser", user);
            return "redirect:/my-products";
        } catch (Exception e) {
            session.setAttribute("errormessage", e.getMessage());
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "logout.html";
    }

    @PostMapping("/register/controller")
    public String registerValidation(
        @RequestParam(value = "company_name", required = false, defaultValue = "") String name,
        @RequestParam(value = "email", required = false, defaultValue = "") String email,
        @RequestParam(value = "ssn", required = false, defaultValue = "") String ssn,
        @RequestParam(value = "register_username", required = false, defaultValue = "") String username,
        @RequestParam(value = "register_password", required = false, defaultValue = "") String password,
        @RequestParam(value = "confirm", required = false, defaultValue = "") String confirm,
        @RequestParam(value = "conditions", required = false, defaultValue = "off") String conditions,
        HttpSession session, Model model) {

        String[] fields = {"name", "email", "ssn","username", "password", "confirm", "conditions"};
        
        Map<String, Boolean> conditionsMap = new HashMap();
        // Name must be filled
        conditionsMap.put("name", !name.equals(""));
        // Email must be filled
        conditionsMap.put("email", !email.equals(""));
        // SSN must be 9 numeric characters long
        conditionsMap.put("ssn", (ssn.length() == 9 && ssn.matches("\\d+")));
        // Username must be at least 4 characters long
        conditionsMap.put("username", username.length() >= 4);
        // Password must be at least 8 characters long
        conditionsMap.put("password", password.length() >= 8);
        // Confirmation password does not match the password
        conditionsMap.put("confirm", password.equals(confirm) && confirm.length() >= 8);
        // CheckBox
        conditionsMap.put("conditions", conditions != null && !conditions.equals("off"));
        
        Map<String, String> errMessages = new HashMap();
        errMessages.put("name", "Please fill in your company's name");
        errMessages.put("email", "Please fill in a valid email address");
        errMessages.put("ssn", "SSN must be 9 numeric characters long");
        errMessages.put("username", "Username must be at least 4 characters long");
        errMessages.put("password", "Password must be at least 8 characters long");
        errMessages.put("confirm", "Confirmation password does not match your password");
        errMessages.put("conditions", "You must agree to terms and conditions");
        
        Map<String, String> types = new HashMap();
        types.put("name", "text");
        types.put("email", "email");
        types.put("ssn", "text");
        types.put("username", "text");
        types.put("password", "password");
        types.put("confirm", "password");
        types.put("conditions", "checkbox");

        Map<String, String> names = new HashMap();
        names.put("name", "company_name");
        names.put("email", "email");
        names.put("ssn", "ssn");
        names.put("username", "register_username");
        names.put("password", "register_password");
        names.put("confirm", "confirm");
        names.put("conditions", "conditions");

        Map<String, String> placeholders = new HashMap();
        placeholders.put("name", "Company Name");
        placeholders.put("email", "Email");
        placeholders.put("ssn", "Company SSN");
        placeholders.put("username", "Username");
        placeholders.put("password", "Password");
        placeholders.put("confirm", "Confirm your Password");
        placeholders.put("conditions", "");

        Map<String, String> values = new HashMap();
        values.put("name", name);
        values.put("email", email);
        values.put("ssn", ssn);
        values.put("username", username);
        values.put("password", password);
        values.put("confirm", confirm);
        values.put("conditions", conditions);


        // Check if all values are true
        boolean allTrue = true;
        for (String field : fields) {
            if (!conditionsMap.get(field)) {
                allTrue = false;
                break;
            }
        }

        User user = null;
        boolean dbError = false;
        try {
            if (allTrue) {
                user = new User(name, email, ssn, username, password);
                us.register(user);
                session.setAttribute("currentuser", user); 
                return "redirect:/my-products";
            }
        } catch (Exception e) {
            session.setAttribute("error", e.getMessage());
            dbError = true;
            allTrue = false;
        }
        
        // Add the needed attributes to continue the registration or to show error messages
        model.addAttribute("conditionsMap", conditionsMap);
        model.addAttribute("errMessages", errMessages);
        model.addAttribute("types", types);
        model.addAttribute("names", names);
        model.addAttribute("placeholders", placeholders);
        model.addAttribute("values", values);
        model.addAttribute("fields", fields);
        model.addAttribute("alltrue", allTrue);
        model.addAttribute("dbError", dbError);

        return "register.html";
    }

}
