package lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.dao;

import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDAO extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByEmail(String email);
}

