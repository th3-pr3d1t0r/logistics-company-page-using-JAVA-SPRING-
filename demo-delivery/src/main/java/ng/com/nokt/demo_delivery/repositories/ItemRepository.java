package ng.com.nokt.demo_delivery.repositories;

import ng.com.nokt.demo_delivery.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}