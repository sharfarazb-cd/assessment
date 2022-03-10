package com.clouddestinations.engg.assessment.controllers;

import com.clouddestinations.engg.assessment.response.APIResponse;
import com.clouddestinations.engg.assessment.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/employee")
@PreAuthorize("isAuthenticated()")
public class EmployeeController {
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public APIResponse getEmployeeList() {
        return userService.getAllUsers();
    }

    @PostMapping(value = "/create")
    public APIResponse uploadExcelToDB(@RequestParam("Excel") MultipartFile file){
        return userService.createOrUpdateUser(file);
    }

    @GetMapping(value = "/{id}")
    public APIResponse getEmployee(@PathVariable String id){
        return userService.getEmployee(id);
    }
}
