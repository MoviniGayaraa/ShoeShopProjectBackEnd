package lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "size")
@Entity
@IdClass(SizeId.class)
public class SizeEntity {
    @Id
    private int size;

    @JsonManagedReference
    @ManyToOne
    @Id
    private InventoryEntity inventory;

    private int quantity;
    private double unit_price_sale;
    private double unit_price_buy;
    private double expected_profit;
    private String status;
    private double profit_margin;

}
