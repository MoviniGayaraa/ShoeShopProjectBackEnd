package lk.ijse.gdse.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "supplier")
public class Supplier implements SuperEntity{
    @Id
    private String supplier_id;
    private String supplier_name;
    @Enumerated(EnumType.STRING)
    private Category category;
    private String address_line_01;
    private String address_line_02;
    private String address_line_03;
    private String address_line_04;
    private String address_line_05;
    private String address_line_06;
    private String contact_no_1;
    private String contact_no_2;
    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    private Set<SupplierInventoryDetail> supplierInventoryDetails = new HashSet<>();
}
