package lk.ijse.gdse.DTO;

import lk.ijse.gdse.Entity.Role;
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
