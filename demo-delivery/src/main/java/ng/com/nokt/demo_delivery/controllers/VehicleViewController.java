package ng.com.nokt.demo_delivery.controllers;

import ng.com.nokt.demo_delivery.entities.Vehicle;
import ng.com.nokt.demo_delivery.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vehicles")
public class VehicleViewController {

    @Autowired
    private VehicleRepository vehicleRepository;

    // Method to display the form for adding a new vehicle
    @GetMapping("/new")
    public String showAddVehicleForm(Model model) {
        model.addAttribute("vehicle", new Vehicle()); // Add an empty vehicle object to the model
        return "add-vehicle"; // Return the name of the Thymeleaf template for adding a vehicle
    }

    // Method to handle the form submission
    @PostMapping
    public String createVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle); // Save the vehicle to the database
        return "redirect:/services"; // Redirect to the services page after saving
    }
}