package com.clouddestinations.engg.assessment.models;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class User {
    @Id
    private String employeeId;
    private String unitName;
    private String employeeName;
    private String dateOfJoining;
    private String classification;
    private String managerId;
    private String managerName;
    private String password;
    private String managerEmail;
    private int level;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Rating> ratings;


    // bi-directional many-to-many association to Role
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "roles", joinColumns = { @JoinColumn(name = "EMPLOYEE_ID") }, inverseJoinColumns = {
            @JoinColumn(name = "ROLE_ID") })
    private Set<Role> roles;

    public User(String employeeId, String unitName, String employeeName, String dateOfJoining, String classification,
            String managerId, String managerName, String password, String managerEmail, int level, Set<Role> roles) {
        this.employeeId = employeeId;
        this.unitName = unitName;
        this.employeeName = employeeName;
        this.dateOfJoining = dateOfJoining;
        this.classification = classification;
        this.managerId = managerId;
        this.managerName = managerName;
        this.password = password;
        this.managerEmail = managerEmail;
        this.level = level;
        this.roles = roles;
    }

    public User() {
    }

    

    public User(String employeeId, String employeeName) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
    }

    @Override
    public String toString() {
        return "User [classification=" + classification + ", dateOfJoining=" + dateOfJoining + ", employeeId="
                + employeeId + ", employeeName=" + employeeName + ", level=" + level + ", managerEmail=" + managerEmail
                + ", managerId=" + managerId + ", managerName=" + managerName + ", password=" + password + ", roles="
                + roles + ", unitName=" + unitName + "]";
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getManagerEmail() {
        return managerEmail;
    }

    public void setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    

    


    // Generated code
    
    
}
