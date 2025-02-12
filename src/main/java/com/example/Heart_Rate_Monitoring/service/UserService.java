package com.example.Heart_Rate_Monitoring.service;

import com.example.Heart_Rate_Monitoring.entity.User;
import com.example.Heart_Rate_Monitoring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean register(User user){
        System.out.println("I am here");
        String email = user.getEmail();
        Optional<User> userExist = userRepository.findByEmail(email);
        if(userExist.isPresent()){
            System.out.println("User is present in db");
            return false;
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public Boolean login(User user){
        Optional<User> userData = userRepository.findByEmail(user.getEmail());
        if(userData.isEmpty()){
            return false;
        }

        // encoded password check
        return passwordEncoder.matches(user.getPassword(), userData.get().getPassword());

    }

}
