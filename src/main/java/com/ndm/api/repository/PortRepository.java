package com.ndm.api.repository;

import com.ndm.api.entity.Port;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortRepository extends JpaRepository<Port, Integer> {
    boolean existsByMacAddress(final String macAddress);
}
