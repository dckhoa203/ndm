package com.ndm.api.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Port {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false, unique = true)
    private String name;
    private String connector;

    @Column(columnDefinition = "boolean default false")
    private boolean state;
    private String speed;

    private String mtu;
    private String mdi;

    @Column(length = 20, nullable = false, unique = true)
    private String macAddress;
}
