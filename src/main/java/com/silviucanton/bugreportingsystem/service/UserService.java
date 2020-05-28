package com.silviucanton.bugreportingsystem.service;

import com.silviucanton.bugreportingsystem.domain.Developer;
import com.silviucanton.bugreportingsystem.domain.Employee;
import com.silviucanton.bugreportingsystem.domain.QATester;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<QATester> findTesterByUsername(String username);

    Optional<Developer> findDeveloperByUsername(String username);

    List<Employee> getAllEmployees();

    void registerTester(Employee tester);

    void registerDeveloper(Employee developer);

    void registerAdmin(Employee admin);

    void deleteEmployee(Integer employeeId);

    Optional<Employee> findEmployeeById(Integer id);
}
