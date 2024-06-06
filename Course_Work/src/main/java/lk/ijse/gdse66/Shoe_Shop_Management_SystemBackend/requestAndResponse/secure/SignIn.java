package lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.requestAndResponse.secure;

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
