package com.example.devTimesheet.repository;

import com.example.devTimesheet.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer> {
    Optional<Branch> findBranchByNameBranch(String nameBranch);
}
