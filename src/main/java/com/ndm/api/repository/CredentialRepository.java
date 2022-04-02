package com.ndm.api.repository;

import com.ndm.api.entity.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialRepository extends JpaRepository<Credential, Integer> {
    boolean existsByName(final String name);
    boolean existsByUsername(final String username);
}
