package com.clouddestinations.engg.assessment.controllers;

import java.util.List;

import com.clouddestinations.engg.assessment.models.User;
import com.clouddestinations.engg.assessment.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/employee")
@PreAuthorize("isAuthenticated()")
public class EmployeeController {
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public ResponseEntity<List<User>> getEmployeeList(){
        return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<User> createEmployee(@RequestBody User user) {       
        return ResponseEntity.ok(userService.createOrUpdateUser(user));
    }
    
}
