package com.silviucanton.bugreportingsystem.repository;

import com.silviucanton.bugreportingsystem.domain.Bug;
import com.silviucanton.bugreportingsystem.domain.BugStatus;
import com.silviucanton.bugreportingsystem.domain.QATester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BugRepository extends JpaRepository<Bug, Integer> {

    List<Bug> findAllByStatusOrStatus(BugStatus status1, BugStatus status2);

    List<Bug> findAllByOrderByStatus();

    List<Bug> findAllByReporterOrderByStatus(QATester tester);
}
