package lk.ijse.gdse.DTO;

import lk.ijse.gdse.Entity.Gender;
import lk.ijse.gdse.Entity.Level;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO {
    private String customer_id;
    private String name;
    private Gender gender;
    private String join_date_as_a_loyalty_customer;
    private Level level;
    private int total_points;
    private String dob;
    private String address_line_1;
    private String address_line_2;
    private String address_line_3;
    private String address_line_4;
    private String address_line_5;
    private String contact_no;
    private String email;
    private String recent_purchase_date_and_time;
}
