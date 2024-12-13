package com.example.devTimesheet.repository;

import com.example.devTimesheet.entity.Client;
import com.example.devTimesheet.entity.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    boolean existsByNameClient(String NameClient);
    Optional<Client> findClientByNameClient(String nameClient);

    Page<Client> findAll(Pageable pageable);
}
