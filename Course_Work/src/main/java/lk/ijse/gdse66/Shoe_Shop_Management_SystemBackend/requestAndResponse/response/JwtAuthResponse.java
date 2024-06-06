package lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.requestAndResponse.response;

import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class JwtAuthResponse {
    private String token;
    private Role role;
}
