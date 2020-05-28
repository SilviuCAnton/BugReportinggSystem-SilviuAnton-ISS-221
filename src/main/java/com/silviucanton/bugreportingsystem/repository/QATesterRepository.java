package com.silviucanton.bugreportingsystem.repository;

import com.silviucanton.bugreportingsystem.domain.QATester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QATesterRepository extends JpaRepository<QATester, Integer> {
    Optional<QATester> findByAccount_Username(String username);
}
