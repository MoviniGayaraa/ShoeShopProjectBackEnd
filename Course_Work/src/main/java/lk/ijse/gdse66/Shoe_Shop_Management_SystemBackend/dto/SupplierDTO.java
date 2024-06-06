package lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.dto;

import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SupplierDTO {
    private String supplier_id;
    private String supplier_name;
    private Category category;
    private String address_line_01;
    private String address_line_02;
    private String address_line_03;
    private String address_line_04;
    private String address_line_05;
    private String address_line_06;
    private String contact_no_1;
    private String contact_no_2;
    private String email;
}
