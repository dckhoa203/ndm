package com.ndm.api.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Interface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(columnDefinition = "boolean default false")
    private boolean state;

    @Column(columnDefinition = "boolean default false")
    private boolean dhcp;

    @Column(length = 20, nullable = false, unique = true)
    private String ipAddress;

    @Column(length = 20, nullable = false)
    private String netmask;

    @Column(length = 20, nullable = false)
    private String gateway;

    private String info;
}
