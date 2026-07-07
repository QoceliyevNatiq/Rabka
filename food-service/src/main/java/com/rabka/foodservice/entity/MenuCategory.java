package com.Rabka.rabka.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "menuCategory")
@NoArgsConstructor
@AllArgsConstructor
public class MenuCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false,length = 50)
    private String name;

    @Column(nullable = false,length = 255)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menuCategory_id")
    private RestaurantMenu menuCategory;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "menuCategory")
    private List<Food> foods;


}
