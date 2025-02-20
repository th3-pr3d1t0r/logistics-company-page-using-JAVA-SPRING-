package ng.com.nokt.demo_delivery.repositories;

import ng.com.nokt.demo_delivery.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query("SELECT v FROM Vehicle v LEFT JOIN FETCH v.items") // Fetch join for items
    List<Vehicle> findAllWithItems();

    // ... other methods if needed
}