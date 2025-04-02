package com.carproject.repo;

import com.carproject.domain.ClientSubmission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientSubmission, Integer> {
}