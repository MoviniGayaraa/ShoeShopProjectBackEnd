package lk.ijse.gdse.service;

import lk.ijse.gdse.DTO.EmployeeDTO;
import lk.ijse.gdse.reqAndresp.response.JwtAuthResponse;
import lk.ijse.gdse.reqAndresp.secure.SignIn;
import lk.ijse.gdse.reqAndresp.secure.SignUp;


public interface AuthenticationService {
    JwtAuthResponse signIn(SignIn signIn);
    JwtAuthResponse signUp(SignUp signUp, EmployeeDTO employeeDTO);
    JwtAuthResponse signUp();
    JwtAuthResponse refreshToken(String tokenAccess);
}
