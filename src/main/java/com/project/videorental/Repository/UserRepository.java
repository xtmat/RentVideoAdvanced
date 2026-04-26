package com.project.videorental.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.videorental.Model.User;


public interface UserRepository extends JpaRepository<User, String> {
    public User findUserByEmail(String email);
    public boolean existsByEmail(String email);
}
