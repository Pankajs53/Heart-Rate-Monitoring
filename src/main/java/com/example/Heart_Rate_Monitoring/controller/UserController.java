package com.example.Heart_Rate_Monitoring.controller;

import com.example.Heart_Rate_Monitoring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.Heart_Rate_Monitoring.entity.User;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/health")
    public String checkhealth(){
        return "everything is Okie";
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user){
        System.out.println("Here" + " "+ user) ;
        boolean ansIs = userService.register(user);
        System.out.println(ansIs);

        if(!ansIs){
            System.out.println("User already exist");
            return new ResponseEntity<>("User Already Exist", HttpStatus.BAD_REQUEST);
        }

        Map<String, String> response = new HashMap<>();
        response.put("message", "User Registered Success");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        //Check if request body is null or fields are missing
        if (user == null || user.getEmail() == null || user.getPassword() == null) {
            return new ResponseEntity<>("Fields cannot be empty", HttpStatus.BAD_REQUEST);
        }

        Map<String, String> response = new HashMap<>();
        response.put("message", "User Registered Success");


        Boolean loginData = userService.login(user);
        if(loginData){
            response.put("message", "LOGGED IN");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }

        response.put("message", "Password Does Not Match");
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);



    }

}
