package com.silviucanton.bugreportingsystem.repository;

import com.silviucanton.bugreportingsystem.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
