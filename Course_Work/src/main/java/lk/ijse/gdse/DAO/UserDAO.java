package lk.ijse.gdse.DAO;

import lk.ijse.gdse.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserDAO extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
}
