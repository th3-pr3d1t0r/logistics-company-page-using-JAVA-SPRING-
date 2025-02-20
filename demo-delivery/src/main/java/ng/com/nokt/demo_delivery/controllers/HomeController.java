package ng.com.nokt.demo_delivery.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home"; // Renders the home.html template
    }

    @GetMapping("/about")
    public String about() {
        return "about"; // Renders the about.html template
    }

}