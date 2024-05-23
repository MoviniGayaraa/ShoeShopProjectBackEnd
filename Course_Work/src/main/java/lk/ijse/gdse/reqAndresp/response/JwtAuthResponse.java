package lk.ijse.gdse.reqAndresp.response;

import lk.ijse.gdse.Entity.Role;
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
