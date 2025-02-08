package ismgroup58.addProduct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
public class RequestController {
    
    @GetMapping("/helloworld")
    public String getHelloWorld() {
        return "Hello, World!";
    }

    @GetMapping("/time")
    public String getServerTime() {
        return "The Current Time of the Server is " + LocalTime.now();
    }
}
