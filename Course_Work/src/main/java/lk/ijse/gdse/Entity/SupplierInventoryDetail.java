package lk.ijse.gdse.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "supplier_inventory_detail")
public class SupplierInventoryDetail implements SuperEntity {
    @Id
    private String supplier_inventory_detail_id;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "item_code")
    private Inventory inventory;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
}
