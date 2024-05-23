package lk.ijse.gdse.DAO;

import lk.ijse.gdse.Entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface SupplierDAO extends JpaRepository<Supplier, String> {
    Optional<Supplier> findByEmail(String email);

    @Query(value = "SELECT supplier_id FROM supplier ORDER BY supplier_id DESC LIMIT 1", nativeQuery = true)
    String findLastId();

    @Query(value = "SELECT supplier_id FROM supplier", nativeQuery = true)
    List<String> getSupplierIds();
}
