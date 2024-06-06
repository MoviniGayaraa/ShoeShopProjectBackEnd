package lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.service;

import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.dto.EmployeeDTO;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.requestAndResponse.response.JwtAuthResponse;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.requestAndResponse.secure.SignIn;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.requestAndResponse.secure.SignUp;

public interface AuthenticationService {
    JwtAuthResponse signIn(SignIn signIn);
    JwtAuthResponse signUp(SignUp signUp, EmployeeDTO employeeDTO);
    JwtAuthResponse signUp();
    JwtAuthResponse refreshToken(String tokenAccess);
}
