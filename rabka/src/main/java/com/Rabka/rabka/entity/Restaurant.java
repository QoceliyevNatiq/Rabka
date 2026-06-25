package com.Rabka.rabka.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 20)
    private String name;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "restaurant")
    private List<RestaurantBranch> branches;

    @OneToOne(fetch = FetchType.LAZY)
    private RestaurantMenu restaurantMenu;

    @Column(unique = true, nullable = false, length = 255)
    private String logoUrl;

    @Column(nullable = false, length = 255)
    private String description;

    private Boolean isActive;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private RestaurantStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private RestaurantType type;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "restaurant")
    private List<WorkingHours> workingHours;

}
