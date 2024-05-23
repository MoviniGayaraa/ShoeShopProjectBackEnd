package lk.ijse.gdse.DAO;

import lk.ijse.gdse.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface CustomerDAO extends JpaRepository<Customer, String> {
    Optional<Customer> findByEmail(String email);

    @Query(value = "SELECT customer_id FROM customer", nativeQuery = true)
    List<String> getCustomerIds();

    @Query(value = "SELECT customer_id FROM customer ORDER BY customer_id DESC LIMIT 1", nativeQuery = true)
    String findLastId();
}
