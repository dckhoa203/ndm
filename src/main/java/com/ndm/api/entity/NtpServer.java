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

    @Column(length = 100, nullable = false, unique = true)
    private String clockName;

    @Column(length = 20, nullable = false, unique = true)
    private String ipAddress;

    @Column(columnDefinition = "boolean default false")
    private boolean state;

    private Integer taiOffset;
    private Integer dscp;
    private Integer vlanPriority;
    private Integer timeInterval;
    private Integer numberMessages;
}
