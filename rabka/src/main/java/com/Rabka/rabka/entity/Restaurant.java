package com.Rabka.rabka.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 20)
    private String address;

    private Location location;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "restaurant")
    private List<RestaurantBranch> branches;

    @OneToOne(fetch = FetchType.LAZY)
    private RestaurantMenu restaurantMenu;



}
