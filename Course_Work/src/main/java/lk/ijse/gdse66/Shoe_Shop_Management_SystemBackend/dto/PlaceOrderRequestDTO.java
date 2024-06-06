package lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class PlaceOrderRequestDTO {
    private String customer_id;
    private String customer_name;
    private double net_total;
    private Date purchase_date;
    private String payment_type;
    private String userEmail;
    private List<OrderItemDTO> items;
}
