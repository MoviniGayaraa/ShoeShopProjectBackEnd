package lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "supplier_inventory_detail")
public class SupplierInventoryDetailEntity implements SuperEntity {
    @Id
    private String supplier_inventory_detail_id;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "item_code")
    private InventoryEntity inventory;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private SupplierEntity supplier;
}
