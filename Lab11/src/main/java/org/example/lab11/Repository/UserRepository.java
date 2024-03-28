package org.example.lab11.Repository;

import org.example.lab11.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByID (Integer Id);
    User findUserByUsernameAndPassword(String username , String password);
}
