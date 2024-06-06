package lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.dao;

import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InventoryDAO extends JpaRepository<InventoryEntity,String> {
    @Query(value = "SELECT item_code FROM inventory WHERE item_code LIKE CONCAT(:occ, '%') ORDER BY item_code DESC LIMIT 1", nativeQuery = true)
    String findLastId(@Param("occ") String occ);

    @Query(value = "SELECT item_code FROM inventory", nativeQuery = true)
    List<String> getItemIds();

    @Query(value = "SELECT size FROM size WHERE inventory_item_code = :itemCode", nativeQuery = true)
    List<String> getSize(@Param("itemCode") String itemCode);
}
