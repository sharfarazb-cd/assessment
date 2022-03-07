package com.clouddestinations.engg.assessment.services;

import com.clouddestinations.engg.assessment.models.Role;
import com.clouddestinations.engg.assessment.repositories.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepo;

    public Role createRoleIfNotFound(String roleName){
        if (roleRepo.existsByName(roleName)) {
            return roleRepo.findByName(roleName);
        } else {
            Role role = new Role(roleName);
            return roleRepo.save(role);
        }
    }
    
}
