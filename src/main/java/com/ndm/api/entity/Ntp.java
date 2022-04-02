package com.ndm.api.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Ntp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "boolean default false")
    private boolean ntpClient;

    private int dscp;
    private int vlanPriority;

    @Column(columnDefinition = "boolean default false")
    private boolean syncStatus;

    private int timeInterval;
    private int numberMessages;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ntp_id", nullable = false)
    private List<NtpServer> ntpServers;
}
