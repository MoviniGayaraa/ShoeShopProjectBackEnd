package lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "sale")
public class SaleEntity implements SuperEntity{
    @Id
    private String order_id;
    private String customer_name;
    private double total_price;
    private Date purchase_date;
    private String payment_method;
    private double added_points;
    private String cashier_name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    private Set<SaleInventoryDetailEntity> saleDetails = new HashSet<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;
}
