package lk.ijse.gdse.DTO;

import lk.ijse.gdse.Entity.InventoryGender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class InventoryDTO {
    private String item_code;
    private String item_desc;
    private int item_qty;
    private String item_pic;
    private String category;
    private double unit_price_sale;
    private double unit_price_buy;
    private double expected_profit;
    private double profit_margin;
    private InventoryGender gender;
    private String occasion;
    private String supplierId;
}
