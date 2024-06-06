package lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SizeId implements Serializable {
    private int size;
    private InventoryEntity inventory;
}
