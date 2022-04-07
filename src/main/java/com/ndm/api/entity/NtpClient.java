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
public class NtpClient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "boolean default false")
    private boolean ntpClient;

    @Column(columnDefinition = "boolean default false")
    private boolean syncStatus;

    private Integer taiOffset;
    private Integer dscp;
    private Integer vlanPriority;
    private Integer timeInterval;
    private Integer numberMessages;
}
