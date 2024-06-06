package lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter

@Entity
@Table(name = "sale_inventory_details")
public class SaleInventoryDetailEntity {
    @Id
    private String orderDetailID;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "item_code")
    private InventoryEntity inventory;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id")
    private SaleEntity sale;

    private String itemName;
    private int quantity;
    private int size;
    private double unitPrice;
    private double subTotal;
}
