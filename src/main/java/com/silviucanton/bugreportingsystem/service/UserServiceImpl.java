package com.silviucanton.bugreportingsystem.service;

import com.silviucanton.bugreportingsystem.domain.Developer;
import com.silviucanton.bugreportingsystem.domain.QATester;
import com.silviucanton.bugreportingsystem.repository.DeveloperRepository;
import com.silviucanton.bugreportingsystem.repository.QATesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final QATesterRepository testerRepository;

    private final DeveloperRepository developerRepository;

    @Autowired
    public UserServiceImpl(QATesterRepository testerRepository, DeveloperRepository developerRepository) {
        this.testerRepository = testerRepository;
        this.developerRepository = developerRepository;
    }


    @Override
    public Optional<QATester> findTesterByUsername(String username) {
        return this.testerRepository.findById(username);
    }

    @Override
    public Optional<Developer> findDeveloperByUsername(String username) {
        return this.developerRepository.findById(username);
    }
}
