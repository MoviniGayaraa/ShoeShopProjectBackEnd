package lk.ijse.gdse.reqAndresp.secure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignIn {
    private String email;
    private String password;
}
