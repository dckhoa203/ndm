package com.ndm.api.repository;

import com.ndm.api.entity.Ntp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NtpRepository extends JpaRepository<Ntp, Integer> {

}
