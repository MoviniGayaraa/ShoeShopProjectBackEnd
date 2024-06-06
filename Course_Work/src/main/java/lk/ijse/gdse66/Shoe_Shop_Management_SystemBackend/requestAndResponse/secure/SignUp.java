package lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.requestAndResponse.secure;

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
