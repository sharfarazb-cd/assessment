package com.clouddestinations.engg.assessment.dto;

import java.util.List;
import java.util.Set;

public class UserDTO {
    private final String employeeId;
    private final String unitName;
    private final String employeeName;
    private final String jobTitle;
    private final String dateOfJoining;
    private final String classification;
    private final String managerId;
    private final String managerName;
    private final String password;
    private final String managerEmail;
    private final int level;
    private final List<RatingDTO> ratings;
    private final Set<RoleDTO> roles;

    public UserDTO(String employeeId, String unitName, String employeeName, String jobTitle, String dateOfJoining,
                   String classification, String managerId, String managerName, String password, String managerEmail,
                   int level, List<RatingDTO> ratings, Set<RoleDTO> roles) {
        this.employeeId = employeeId;
        this.unitName = unitName;
        this.employeeName = employeeName;
        this.jobTitle = jobTitle;
        this.dateOfJoining = dateOfJoining;
        this.classification = classification;
        this.managerId = managerId;
        this.managerName = managerName;
        this.password = password;
        this.managerEmail = managerEmail;
        this.level = level;
        this.ratings = ratings;
        this.roles = roles;
    }

    public String getEmployeeId() {return employeeId;}

    public String getUnitName() {return unitName;}

    public String getEmployeeName() {return employeeName;}

    public String getDateOfJoining() {return dateOfJoining;}

    public String getClassification() {return classification;}

    public String getManagerId() {return managerId;}

    public String getManagerName() {return managerName;}

    public String getPassword() {return password;}

    public String getManagerEmail() {return managerEmail;}

    public int getLevel() {return level;}

    public List<RatingDTO> getRatings() {return ratings;}

    public Set<RoleDTO> getRoles() {return roles;}
}
