package ng.com.nokt.demo_delivery.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double fuelCapacity;
    private String plateNumber;
    private String status;
    private String type;
    private double vehicleWeight;


    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VehicleItem> items = new ArrayList<>();


    public double getRemainingWeight() {
        double totalItemWeight = items.stream()
                .filter(vi -> vi.getItem() != null) // Handle potential null items
                .mapToDouble(vi -> vi.getItem().getWeight())
                .sum();
        return vehicleWeight - totalItemWeight;
    }

    // Getters and Setters (Important!)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getFuelCapacity() { return fuelCapacity; }
    public void setFuelCapacity(double fuelCapacity) { this.fuelCapacity = fuelCapacity; }
    public String getPlateNumber() { return plateNumber; }
    public void setPlateNumber(String plateNumber) { this.plateNumber = plateNumber; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public double getVehicleWeight() { return vehicleWeight; }
    public void setVehicleWeight(double vehicleWeight) { this.vehicleWeight = vehicleWeight; }
    public List<VehicleItem> getItems() { return items; }
    public void setItems(List<VehicleItem> items) { this.items = items; }
}