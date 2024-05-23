package lk.ijse.gdse.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lk.ijse.gdse.DAO.UserDAO;
import lk.ijse.gdse.DTO.EmployeeDTO;
import lk.ijse.gdse.DTO.UserDTO;
import lk.ijse.gdse.Entity.Employee;
import lk.ijse.gdse.Entity.Role;
import lk.ijse.gdse.Entity.User;
import lk.ijse.gdse.conversion.Mapping;
import lk.ijse.gdse.reqAndresp.response.JwtAuthResponse;
import lk.ijse.gdse.reqAndresp.secure.SignIn;
import lk.ijse.gdse.reqAndresp.secure.SignUp;
import lk.ijse.gdse.service.AuthenticationService;
import lk.ijse.gdse.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private  final UserDAO userDAO;
    private final Mapping mapping;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public JwtAuthResponse signIn(SignIn signIn) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signIn.getEmail(), signIn.getPassword()));
        var userByEmail = userDAO.findByEmail(signIn.getEmail()).orElseThrow(() -> new UsernameNotFoundException("Email not found"));
        String token = jwtService.generateToken(userByEmail);
        Role role = userByEmail.getRole();
        return JwtAuthResponse.builder()
                .token(token)
                .role(role)
                .build();
    }

    @Override
    public JwtAuthResponse signUp(SignUp signUp, EmployeeDTO employeeDTO) {
        UserDTO build = UserDTO.builder()
                .userId(UUID.randomUUID().toString())
                .email(signUp.getEmail())
                .password(passwordEncoder.encode(signUp.getPassword()))
                .role(Role.valueOf(signUp.getRole()))
                .build();

        User save = mapping.toUser(build);
        Employee employee = new Employee();
        employee.setEmployeeId(employeeDTO.getEmployeeId());
        save.setEmployee(employee);

        User user = userDAO.save(save);
        String generateToken = jwtService.generateToken(user);
        return JwtAuthResponse.builder().token(generateToken).build();
    }


    //Only One time
    @Override
    public JwtAuthResponse signUp() {
        Long rowCount = (Long) entityManager.createNativeQuery("SELECT COUNT(*) FROM user").getSingleResult();

        if (rowCount == null || rowCount == 0) {

            UserDTO build = UserDTO.builder()
                    .userId(UUID.randomUUID().toString())
                    .email("test@gmail.com")
                    .password(passwordEncoder.encode("1234"))
                    .role(Role.valueOf("ADMIN"))
                    .build();

            User user = userDAO.save(mapping.toUser(build));
            String generateToken = jwtService.generateToken(user);
            return JwtAuthResponse.builder().token(generateToken).build();

        } else {
            return JwtAuthResponse.builder().token("User table is not empty").build();
        }
    }

    @Override
    public JwtAuthResponse refreshToken(String tokenAccess) {
        String username = jwtService.extractUsername(tokenAccess);
        User user = userDAO.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Email not found"));
        String refreshToken = jwtService.generateToken(user);
        return JwtAuthResponse.builder().token(refreshToken).build();
    }
}
