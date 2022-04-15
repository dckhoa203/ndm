package com.ndm.api.repository;

import com.ndm.api.entity.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredentialRepository extends JpaRepository<Credential, Integer> {
    boolean existsByName(final String name);
    boolean existsByUsername(final String username);
    @Query("SELECT credential FROM Credential credential JOIN credential.devices device WHERE device.id = :deviceId")
    Optional<Credential> findCredentialByDeviceId(@Param("deviceId") final int deviceId);
}
