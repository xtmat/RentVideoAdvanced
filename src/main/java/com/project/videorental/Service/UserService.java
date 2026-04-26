package com.project.videorental.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.project.videorental.Model.User;
import com.project.videorental.Repository.UserRepository;

@Service
public class UserService implements UserDetailsService{
    
    @Autowired
    private UserRepository userRepository;

    public User loadUserByUsername(String email){
        return userRepository.findUserByEmail(email);
    }
}
