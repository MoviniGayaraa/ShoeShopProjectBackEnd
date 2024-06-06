package lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.dto;

import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTO {
    private String userId;
    private String email;
    private String password;
    private Role role;
}
