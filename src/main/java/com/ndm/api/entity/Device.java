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
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    private String name;

    @Column(length = 100)
    private String label;

    @Column(length = 100)
    private String serial;

    @Column(nullable = false)
    private int port;

    @Column(length = 20, nullable = false, unique = true)
    private String ipAddress;

    @Column(length = 100)
    private String version;

    @Column(columnDefinition = "boolean default false")
    private boolean isOperational;

    @Column(columnDefinition = "boolean default false")
    private boolean isResync;

    private String updatedAt;
    private Integer type;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ntp_id", nullable = false)
    private Ntp ntp;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "device_id", nullable = false)
    private List<Interface> interfaces;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "device_id", nullable = false)
    private List<Port> ports;
}
