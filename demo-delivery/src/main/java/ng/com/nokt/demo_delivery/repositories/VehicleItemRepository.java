package ng.com.nokt.demo_delivery.repositories;

import ng.com.nokt.demo_delivery.entities.VehicleItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleItemRepository extends JpaRepository<VehicleItem, Long> {
    // No additional methods needed in this basic example.
    // Spring Data JPA provides basic CRUD operations.
}