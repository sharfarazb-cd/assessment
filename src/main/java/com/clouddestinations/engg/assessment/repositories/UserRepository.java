package com.clouddestinations.engg.assessment.repositories;

import com.clouddestinations.engg.assessment.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByEmployeeId(String employeeId);
}
