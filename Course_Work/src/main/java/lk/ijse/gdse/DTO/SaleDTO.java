package lk.ijse.gdse.DTO;

import lk.ijse.gdse.Entity.Customer;
import lk.ijse.gdse.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class SaleDTO {
    private String  order_id;
    private String customer_name;
    private double total_price;
    private Date purchase_date;
    private String payment_method;
    private double added_points;
    private String cashier_name;
    private User user;
    private Customer customer;
}
