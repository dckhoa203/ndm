package com.ndm.api.repository;

import com.ndm.api.entity.NtpServer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NtpServerRepository extends JpaRepository<NtpServer, Integer> {
    boolean existsByIpAddress(final String ipAddress);
}
