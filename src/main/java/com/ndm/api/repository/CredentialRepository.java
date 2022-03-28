package com.ndm.api.repository;

import com.ndm.api.entity.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CredentialRepository extends JpaRepository<Credential, Integer> {
    @Query("SELECT cr FROM Credential cr WHERE is_deleted =0")
    List<Credential> getAll();

    boolean existsByName(final String name);

    @Query("SELECT CASE WHEN COUNT(cr) > 0 THEN true ELSE false END " +
            "FROM Credential cr " +
            "WHERE name = :name AND id <> :id AND is_deleted =0")
    boolean existsByNameNotById(@Param("name") final String name,
                                @Param("id") final int id);

    boolean existsByUsername(final String username);

    @Query("SELECT CASE WHEN COUNT(cr) > 0 THEN true ELSE false END " +
            "FROM Credential cr " +
            "WHERE username = :username AND id <> :id AND is_deleted =0")
    boolean existsByUsernameNotById(@Param("username") final String username,
                                    @Param("id") final int id);

    Optional<Credential> findCredentialById(final Integer id);
}
