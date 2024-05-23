package lk.ijse.gdse.reqAndresp.secure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SignUp {
    private String email;
    private String password;
    private String role;
}
