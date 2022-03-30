package com.ndm.api.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

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

    @Column(length = 100, nullable = false, unique = true)
    private String name;

    @Column(length = 100)
    private String label;

    @Column(length = 100)
    private String serial;

    @Column(length = 20, nullable = false, unique = true)
    private String ipAddress;

    @Column(length = 100)
    private String version;

    @Column(columnDefinition = "boolean default false")
    private boolean isOperational;

    @Column(columnDefinition = "boolean default false")
    private boolean isResync;

    private String updatedAt;
    private int type;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ntp_id")
    private Ntp ntp;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "device_id")
    private Set<Interface> interfaces;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "device_id")
    private Set<Port> ports;
}
