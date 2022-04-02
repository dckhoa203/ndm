package com.ndm.api.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class NtpServer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String clockName;

    @Column(length = 20, nullable = false, unique = true)
    private String ipAddress;

    @Column(columnDefinition = "boolean default false")
    private boolean state;
}
