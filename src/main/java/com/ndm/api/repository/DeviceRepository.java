package com.ndm.api.repository;

import com.ndm.api.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {
    Device getByIpAddress(final String ipAddress);
    List<Device> getByType(final int type);
    boolean existsByIpAddress(final String ipAddress);
}
