package com.silviucanton.bugreportingsystem.service;

import com.silviucanton.bugreportingsystem.domain.Bug;
import com.silviucanton.bugreportingsystem.domain.BugStatus;

import java.util.List;
import java.util.Optional;

public interface BugService {

    public void saveBug(Bug bug);

    public void updateBug(Bug theBug);

    public Bug findBugById(int id);

    public List<Bug> getUnsolvedBugs();

    public List<Bug> getAllBugs();

    public void deleteBug(Bug bug);
}
