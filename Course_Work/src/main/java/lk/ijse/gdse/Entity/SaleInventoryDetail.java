package lk.ijse.gdse.Entity;

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
public class SaleInventoryDetail {
    @Id
    private String orderDetailID;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "item_code")
    private Inventory inventory;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Sale sale;

    private String itemName;
    private int quantity;
    private int size;
    private double unitPrice;
    private double subTotal;
}
