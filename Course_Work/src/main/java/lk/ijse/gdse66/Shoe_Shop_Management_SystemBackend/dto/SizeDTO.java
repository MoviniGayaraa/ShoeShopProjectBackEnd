package lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class SizeDTO {
    private int size;
    private String item_code;
    private int quantity;
    private double unit_price_sale;
    private double unit_price_buy;
    private double expected_profit;
    private String status;
    private double profit_margin;
}
