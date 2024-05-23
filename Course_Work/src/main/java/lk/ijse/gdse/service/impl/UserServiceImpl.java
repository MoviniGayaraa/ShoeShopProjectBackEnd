package lk.ijse.gdse.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse.DAO.UserDAO;
import lk.ijse.gdse.DTO.UserDTO;
import lk.ijse.gdse.conversion.Mapping;
import lk.ijse.gdse.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final Mapping map;

    @Override
    public void save(UserDTO userDTO) throws Exception {
        map.toUserDTO(userDAO.save(map.toUser(userDTO)));
    }

    @Override
    public UserDetailsService userDetailsService() {
        return username ->
                userDAO.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
