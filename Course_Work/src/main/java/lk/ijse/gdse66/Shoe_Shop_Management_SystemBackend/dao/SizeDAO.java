package lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.dao;

import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.entity.SizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SizeDAO extends JpaRepository<SizeEntity, String> {
    @Query(value = "SELECT size_id FROM size ORDER BY size_id DESC LIMIT 1", nativeQuery = true)
    String findLastId();

    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END FROM size WHERE size = :sizeId AND inventory_item_code = :itemCode", nativeQuery = true)
    Long countBySizeIdAndItemCode(@Param("sizeId") String sizeId, @Param("itemCode") String itemCode);

    @Modifying
    @Query(value = "DELETE FROM size WHERE inventory_item_code = :itemCode AND size = :sizeId", nativeQuery = true)
    int deleteByItemCodeAndSizeId(@Param("itemCode") String itemCode, @Param("sizeId") String sizeId);

    @Query(value = "SELECT * FROM size WHERE inventory_item_code = :itemCode AND size = :itemSize", nativeQuery = true)
    SizeEntity getDataWithSizeAndItemID(@Param("itemCode") String itemCode, @Param("itemSize") String itemSize);
}
