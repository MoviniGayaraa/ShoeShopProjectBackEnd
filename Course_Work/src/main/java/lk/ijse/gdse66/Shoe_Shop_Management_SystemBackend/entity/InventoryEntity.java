package lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "inventory")
@ToString
public class InventoryEntity implements SuperEntity{
    @Id
    private String item_code;
    private String item_desc;
    @Column(columnDefinition = "LONGTEXT")
    private String item_pic;
    private String category;
    @Enumerated(EnumType.STRING)
    private InventoryGender gender;
    private String occasion;

    @JsonIgnore
    @OneToMany(mappedBy = "inventory")
    private Set<SaleInventoryDetailEntity> saleDetails = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "inventory")
    private Set<SupplierInventoryDetailEntity> supplierInventoryDetails = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "inventory")
    private List<SizeEntity> sizes = new ArrayList<>();
}
