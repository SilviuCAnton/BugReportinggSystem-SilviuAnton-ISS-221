package com.silviucanton.bugreportingsystem.service;

import com.silviucanton.bugreportingsystem.domain.Bug;
import com.silviucanton.bugreportingsystem.domain.BugStatus;
import com.silviucanton.bugreportingsystem.domain.QATester;
import com.silviucanton.bugreportingsystem.repository.BugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BugServiceImpl implements BugService {

    private final BugRepository bugRepository;

    @Autowired
    public BugServiceImpl(BugRepository bugRepository) {
        this.bugRepository = bugRepository;
    }

    @Override
    public void saveBug(Bug bug) {
        bug.setId(0);
        bug.setTimeReported(LocalDateTime.now());
        bug.setStatus(BugStatus.NOT_TRACKED);
        bugRepository.save(bug);
    }

    @Override
    public void updateBug(Bug theBug) {
        Optional<Bug> bug = bugRepository.findById(theBug.getId());
        if(bug.isPresent()){
            bugRepository.save(theBug);
        } else {
            throw new RuntimeException("Bug does not exist");
        }
    }

    @Override
    public Bug findBugById(int id) {
        Optional<Bug> theBug = bugRepository.findById(id);
        return theBug.orElse(null);
    }

    @Override
    public List<Bug> getUnsolvedBugs() {
        return bugRepository.findAllByStatusOrStatus(BugStatus.NOT_TRACKED, BugStatus.TRACKED);
    }

    @Override
    public List<Bug> getAllBugs(QATester currentTester) {
        return bugRepository.findAllByReporterOrderByStatus(currentTester);
    }

    @Override
    public void deleteBug(Bug bug) {
        bugRepository.delete(bug);
    }



    @Override
    public void markBugAsSolved(int bugId) {
        Optional<Bug> theBug = bugRepository.findById(bugId);

        if(theBug.isPresent()){
            Bug bugToUpdate = theBug.get();
            bugToUpdate.setStatus(BugStatus.SOLVED);
            bugRepository.save(bugToUpdate);
        }
    }

    @Override
    public void trackBug(int bugId) {
        Optional<Bug> theBug = bugRepository.findById(bugId);

        if(theBug.isPresent()){
            Bug bugToUpdate = theBug.get();
            bugToUpdate.setStatus(BugStatus.TRACKED);
            bugRepository.save(bugToUpdate);
        }
    }
}
