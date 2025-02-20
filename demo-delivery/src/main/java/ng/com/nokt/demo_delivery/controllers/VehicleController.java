package ng.com.nokt.demo_delivery.controllers;

import ng.com.nokt.demo_delivery.entities.Vehicle;
import ng.com.nokt.demo_delivery.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleRepository;

    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAllWithItems(); // Use the repository method with fetch join
    }

    @PostMapping
    public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @GetMapping("/{id}")
    public Vehicle getVehicleById(@PathVariable Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found")); // Or a custom exception
    }

    // Add this if you need to update an existing vehicle
    @PutMapping("/{id}")
    public Vehicle updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicle) {
        Vehicle existingVehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        // Update the existingVehicle's properties with the values from the request body
        existingVehicle.setName(vehicle.getName());
        existingVehicle.setFuelCapacity(vehicle.getFuelCapacity());
        existingVehicle.setPlateNumber(vehicle.getPlateNumber());
        existingVehicle.setStatus(vehicle.getStatus());
        existingVehicle.setType(vehicle.getType());
        existingVehicle.setVehicleWeight(vehicle.getVehicleWeight());
        // ... update other properties

        return vehicleRepository.save(existingVehicle);
    }


    @DeleteMapping("/{id}")
    public void deleteVehicle(@PathVariable Long id) {
        vehicleRepository.deleteById(id);
    }
}