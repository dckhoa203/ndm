package com.ndm.api.repository;

import com.ndm.api.entity.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CredentialRepository extends JpaRepository<Credential, Integer> {
    @Query("SELECT cr FROM Credential cr WHERE is_deleted =0")
    List<Credential> getAll();

    boolean existsByName(final String name);
    boolean existsByUsername(final String username);
}
