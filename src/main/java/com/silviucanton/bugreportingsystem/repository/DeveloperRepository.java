package com.silviucanton.bugreportingsystem.repository;

import com.silviucanton.bugreportingsystem.domain.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Integer> {
    Optional<Developer> findByAccount_Username(String username);
}
