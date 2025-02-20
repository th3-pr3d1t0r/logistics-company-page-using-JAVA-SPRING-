package ng.com.nokt.demo_delivery.controllers;

import ng.com.nokt.demo_delivery.entities.Vehicle;
import ng.com.nokt.demo_delivery.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ServicesController { // Create a dedicated controller for services

    @Autowired
    private VehicleRepository vehicleRepository;

    @GetMapping("/services")
    public String services(Model model) { // Add Model parameter
        List<Vehicle> vehicles = vehicleRepository.findAllWithItems(); // Fetch vehicles with items

        model.addAttribute("vehicles", vehicles); // Add vehicles to the model
        return "services"; // Renders the services.html template
    }
}