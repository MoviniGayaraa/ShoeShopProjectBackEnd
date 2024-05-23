package lk.ijse.gdse.Entity;

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
public class Inventory implements SuperEntity{
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
    private Set<SaleInventoryDetail> saleDetails = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "inventory")
    private Set<SupplierInventoryDetail> supplierInventoryDetails = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "inventory")
    private List<Size> sizes = new ArrayList<>();
}
