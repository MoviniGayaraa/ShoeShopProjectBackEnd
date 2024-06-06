package lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.service;

import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    void save(UserDTO userDTO) throws Exception;
    UserDetailsService userDetailsService();
}
