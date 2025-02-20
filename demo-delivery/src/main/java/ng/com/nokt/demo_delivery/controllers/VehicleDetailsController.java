
package ng.com.nokt.demo_delivery.controllers;

import jakarta.transaction.Transactional;
import ng.com.nokt.demo_delivery.entities.Item;
import ng.com.nokt.demo_delivery.entities.Vehicle;
import ng.com.nokt.demo_delivery.entities.VehicleItem;
import ng.com.nokt.demo_delivery.repositories.ItemRepository;
import ng.com.nokt.demo_delivery.repositories.VehicleItemRepository;
import ng.com.nokt.demo_delivery.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model; // Import Model
import org.springframework.web.bind.annotation.PostMapping;

@Controller // This is a Controller, not a RestController for serving HTML
public class VehicleDetailsController {

    @GetMapping("/vehicles/{id}/details") // For displaying the page initially
    public String getVehicleDetailsPage(@PathVariable Long id, Model model) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        model.addAttribute("vehicle", vehicle);

        // Crucial: Add the item object to the model for the form
        model.addAttribute("item", new Item()); // Add a new Item object

        return "vehicle-details";
    }


    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ItemRepository itemRepository; // Inject ItemRepository

    @Autowired
    private VehicleItemRepository vehicleItemRepository;

    @PostMapping("/vehicles/{vehicleId}/items") // New endpoint for adding items
    @Transactional
    public String addItemToVehicle(@PathVariable Long vehicleId, @ModelAttribute Item item, Model model) { // Use @ModelAttribute
        try {
            Vehicle vehicle = vehicleRepository.findById(vehicleId)
                    .orElseThrow(() -> new RuntimeException("Vehicle not found"));

            double randomNumber = Math.floor(1000000 + Math.random() * 9000000);
            String itemCode = String.valueOf((int) randomNumber);
            item.setItemCode(itemCode);

            Item savedItem = itemRepository.saveAndFlush(item);

            VehicleItem vehicleItem = new VehicleItem();
            vehicleItem.setVehicle(vehicle);
            vehicleItem.setItem(savedItem);
            vehicleItemRepository.saveAndFlush(vehicleItem);

            // Redirect back to the vehicle details page after adding the item
            return "redirect:/vehicles/" + vehicleId + "/details";

        } catch (Exception e) {
            System.err.println("Error adding item: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("errorMessage", "Error adding item: " + e.getMessage()); // Add error message to the model
            model.addAttribute("vehicle", vehicleRepository.findById(vehicleId).orElseThrow(() -> new RuntimeException("Vehicle not found"))); // Re-add vehicle
            return "vehicle-details"; // Return to the same page to show the error
        }
    }
}
