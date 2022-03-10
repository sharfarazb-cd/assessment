package com.clouddestinations.engg.assessment.services;

import java.util.*;

import com.clouddestinations.engg.assessment.common.AssessmentConstant;
import com.clouddestinations.engg.assessment.common.ResourceProvider;
import com.clouddestinations.engg.assessment.models.Role;
import com.clouddestinations.engg.assessment.models.User;
import com.clouddestinations.engg.assessment.repositories.UserRepository;

import com.clouddestinations.engg.assessment.response.APIResponse;
import com.clouddestinations.engg.assessment.services.fileservice.ExcelService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private EncoderService encoder;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ExcelService excelService;


    public APIResponse getAllUsers() {
        APIResponse apiResponse = new APIResponse();
        apiResponse.setData(userRepo.findAll());
        return apiResponse;
    }

    public APIResponse createOrUpdateUser(MultipartFile file) {
        List<User> result = new ArrayList<>();
        APIResponse apiResponse = new APIResponse();
        if (ExcelService.checkExtension(file)) {
            JSONArray jsonArray = excelService.convertExcelToJSON(file);
            List<User> userList = Arrays.asList(excelService.convertJSONToList(jsonArray, User[].class));
            userList.forEach(user -> result.add(createOrUpdateUser(user)));
            apiResponse.setData(result);
        } else {
            apiResponse.setError(AssessmentConstant.NOT_EXCEL_FILE);
            apiResponse.setStatus(500);
        }
        return apiResponse;
    }

    public User createOrUpdateUser(User user) {

        user.setPassword(encoder.getPasswordEncoder().encode("iamtheuser"));

        //Extract and create the manager. If already exists, add Manager role to it.
        if (userRepo.existsById(user.getManagerId())) {
            User manager = userRepo.findByEmployeeId(user.getManagerId());
            manager.setRoles(addAndGetRoles(manager, Role.MANAGER));
            userRepo.save(manager);
        } else {
            User manager = new User(user.getManagerId(), user.getManagerName());
            createOrUpdateManager(manager);
        }
        //Get current roles if user already exists.
        if (userRepo.existsById(user.getEmployeeId())) {
            User existingUser = userRepo.findByEmployeeId(user.getEmployeeId());
            user.setRoles(addAndGetRoles(existingUser, Role.USER));
        } else {
            user.setRoles(addAndGetRoles(user, Role.USER));
        }
        return userRepo.save(user);
    }

    private void createOrUpdateManager(User manager) {
        manager.setPassword(encoder.getPasswordEncoder().encode("iamthemanager"));
        manager.setRoles(addAndGetRoles(manager, Role.MANAGER));
        userRepo.save(manager);
    }

    public APIResponse getEmployee(String id) {
        APIResponse apiResponse = new APIResponse();
        User existingUser = userRepo.findByEmployeeId(id);
        apiResponse.setData(existingUser);
        return apiResponse;
    }

    private Set<Role> addAndGetRoles(User user, String role) {
        Set<Role> roles = new HashSet<>(Arrays.asList(roleService.createRoleIfNotFound(role)));
        //roles.addAll(user.getRoles());
        if (user.getRoles() == null) {
            return roles;
        } else {
            return getRoles(user, roles);
        }
    }

    private HashSet<Role> getRoles(User user, Set<Role> role) {
        HashSet<Role> roles = new HashSet<>();
        roles.addAll(role);
        roles.addAll(user.getRoles());
        return roles;
    }

}
