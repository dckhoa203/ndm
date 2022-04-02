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

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100)
    private String connector;

    @Column(columnDefinition = "boolean default false")
    private boolean state;

    @Column(length = 10)
    private String speed;

    @Column(length = 10)
    private String mtu;

    @Column(length = 10)
    private String mdi;

    @Column(length = 20, nullable = false, unique = true)
    private String macAddress;
}
