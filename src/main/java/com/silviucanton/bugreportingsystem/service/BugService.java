package com.silviucanton.bugreportingsystem.service;

import com.silviucanton.bugreportingsystem.domain.Bug;
import com.silviucanton.bugreportingsystem.domain.BugStatus;
import com.silviucanton.bugreportingsystem.domain.QATester;

import java.util.List;
import java.util.Optional;

public interface BugService {

    void saveBug(Bug bug);

    void updateBug(Bug theBug);

    Bug findBugById(int id);

    List<Bug> getUnsolvedBugs();

    List<Bug> getAllBugs(QATester currentTester);

    void deleteBug(Bug bug);
}
