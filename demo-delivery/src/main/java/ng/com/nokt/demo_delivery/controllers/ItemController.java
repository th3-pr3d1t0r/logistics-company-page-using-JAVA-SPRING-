package ng.com.nokt.demo_delivery.controllers;

import ng.com.nokt.demo_delivery.entities.Item;
import ng.com.nokt.demo_delivery.entities.Vehicle;
import ng.com.nokt.demo_delivery.entities.VehicleItem;
import ng.com.nokt.demo_delivery.repositories.ItemRepository;
import ng.com.nokt.demo_delivery.repositories.VehicleRepository;
import ng.com.nokt.demo_delivery.repositories.VehicleItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleItemRepository vehicleItemRepository;

    @PostMapping("/{vehicleId}")
    @Transactional
    public ResponseEntity<?> createItem(@PathVariable Long vehicleId, @RequestBody Item item) {
        try {
            Vehicle vehicle = vehicleRepository.findById(vehicleId)
                    .orElseThrow(() -> new RuntimeException("Vehicle not found"));

            double randomNumber = Math.floor(1000000 + Math.random() * 9000000);
            String itemCode = String.valueOf((int) randomNumber);
            item.setItemCode(itemCode);


            Item savedItem = itemRepository.saveAndFlush(item); // Item is saved and ID generated

            VehicleItem vehicleItem = new VehicleItem();
            vehicleItem.setVehicle(vehicle);
            vehicleItem.setItem(savedItem); // This is where you set the saved Item
            vehicleItemRepository.saveAndFlush(vehicleItem); // Save VehicleItem

            return ResponseEntity.ok(vehicleItem); // Return the saved VehicleItem

        } catch (Exception e) {
            System.err.println("Error adding item: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error adding item: " + e.getMessage());
        }
    }
}

