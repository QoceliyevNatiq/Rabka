package com.rabka.restaurantservice.entity;

import jakarta.persistence.*;
import lombok.*;


import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "restaurantMenu")
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, unique = true, length = 20)
    private String restaurantName;

   // @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurantMenu")
    // private List<MenuCategory> category;

   @OneToOne(fetch = FetchType.LAZY, mappedBy = "restaurantMenu" )
   private Restaurant restaurant;

}
