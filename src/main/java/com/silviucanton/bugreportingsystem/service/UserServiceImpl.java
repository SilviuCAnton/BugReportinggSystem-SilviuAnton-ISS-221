package com.silviucanton.bugreportingsystem.service;

import com.silviucanton.bugreportingsystem.domain.Admin;
import com.silviucanton.bugreportingsystem.domain.Developer;
import com.silviucanton.bugreportingsystem.domain.Employee;
import com.silviucanton.bugreportingsystem.domain.QATester;
import com.silviucanton.bugreportingsystem.repository.AdminRepository;
import com.silviucanton.bugreportingsystem.repository.DeveloperRepository;
import com.silviucanton.bugreportingsystem.repository.EmployeeRepository;
import com.silviucanton.bugreportingsystem.repository.QATesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final QATesterRepository testerRepository;

    private final DeveloperRepository developerRepository;

    private final AdminRepository adminRepository;

    private final EmployeeRepository employeeRepository;

    @Autowired
    public UserServiceImpl(QATesterRepository testerRepository, DeveloperRepository developerRepository, AdminRepository adminRepository, EmployeeRepository employeeRepository) {
        this.testerRepository = testerRepository;
        this.developerRepository = developerRepository;
        this.adminRepository = adminRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Optional<QATester> findTesterByUsername(String username) {
        return this.testerRepository.findByAccount_Username(username);
    }

    @Override
    public Optional<Developer> findDeveloperByUsername(String username) {
        return this.developerRepository.findByAccount_Username(username);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return this.employeeRepository.findAll();
    }

    @Override
    public void registerTester(Employee tester) {
        this.testerRepository.save(new QATester(tester));
    }

    @Override
    public void registerDeveloper(Employee developer) {
        this.developerRepository.save(new Developer(developer));
    }

    @Override
    public void registerAdmin(Employee admin) {
        this.adminRepository.save(new Admin(admin));
    }

    @Override
    public void deleteEmployee(Integer employeeId) {
        this.employeeRepository.deleteById(employeeId);
    }

    @Override
    public Optional<Employee> findEmployeeById(Integer id) {
        return this.employeeRepository.findById(id);
    }
}
