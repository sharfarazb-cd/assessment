package com.clouddestinations.engg.assessment.services;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.clouddestinations.engg.assessment.models.Role;
import com.clouddestinations.engg.assessment.models.User;
import com.clouddestinations.engg.assessment.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private EncoderService encoder;

    @Autowired
    private RoleService roleService;

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }


    public User createOrUpdateUser(User user){
        user.setPassword(encoder.getPasswordEncoder().encode("iamtheuser"));

        //Extract and create the manager. If already exists, add Manager role to it.
        if (userRepo.existsById(user.getManagerId())){
            User manager = userRepo.findByEmployeeId(user.getManagerId());
            manager.setRoles(addAndGetRoles(manager, Role.MANAGER));
            userRepo.save(manager);
        } else{
            User manager = new User(user.getManagerId(), user.getManagerName());
            createOrUpdateManager(manager);
        }
        //Get current roles if user already exists.
        if(userRepo.existsById(user.getEmployeeId())){
            User existingUser = userRepo.findByEmployeeId(user.getEmployeeId());
            user.setRoles(addAndGetRoles(existingUser, Role.USER));
        } else {
            user.setRoles(addAndGetRoles(user, Role.USER));
        }
        return userRepo.save(user);
        
    }

    private void createOrUpdateManager(User manager){
        manager.setPassword(encoder.getPasswordEncoder().encode("iamthemanager"));
        manager.setRoles(addAndGetRoles(manager, Role.MANAGER));
        userRepo.save(manager);
    }

    private Set<Role> addAndGetRoles(User user, String role){
        Set<Role> roles = new HashSet<Role>(Arrays.asList(roleService.createRoleIfNotFound(role)));
        //roles.addAll(user.getRoles());
        if (user.getRoles() == null) {
            return roles;
        } else {
            return new HashSet<>(){{
                addAll(roles);
                addAll(user.getRoles());
            }};
        }
    }


    
}
