package ng.com.nokt.demo_delivery.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ng.com.nokt.demo_delivery.entities.Item;
import ng.com.nokt.demo_delivery.entities.Vehicle;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne  // Many VehicleItems to one Vehicle
    @JoinColumn(name = "vehicle_id") // The foreign key column in vehicle_item
    private Vehicle vehicle;

    @ManyToOne  // Many VehicleItems to one Item
    @JoinColumn(name = "item_id") // The foreign key column in vehicle_item
    private Item item; // Correct: Reference the Item entity
}