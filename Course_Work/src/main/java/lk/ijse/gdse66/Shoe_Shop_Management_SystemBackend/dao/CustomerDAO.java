package lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.dao;

import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerDAO extends JpaRepository<CustomerEntity, String> {
    Optional<CustomerEntity> findByEmail(String email);

    @Query(value = "SELECT customer_id FROM customer", nativeQuery = true)
    List<String> getCustomerIds();

    @Query(value = "SELECT customer_id FROM customer ORDER BY customer_id DESC LIMIT 1", nativeQuery = true)
    String findLastId();
}
