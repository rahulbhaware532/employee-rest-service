package com.employeerestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employeerestservice.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
