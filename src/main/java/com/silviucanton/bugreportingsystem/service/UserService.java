package com.silviucanton.bugreportingsystem.service;

import com.silviucanton.bugreportingsystem.domain.Developer;
import com.silviucanton.bugreportingsystem.domain.QATester;

import java.util.Optional;

public interface UserService {

    Optional<QATester> findTesterByUsername(String username);

    Optional<Developer> findDeveloperByUsername(String username);
}
