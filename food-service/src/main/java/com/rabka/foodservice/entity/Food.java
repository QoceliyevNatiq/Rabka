package com.rabka.foodservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "food")
@NoArgsConstructor
@AllArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 20)
    private String name;

    @Column(nullable = false,length = 255)
    private String description;

    @Column(nullable = false,length = 10)
    private BigDecimal price;

    @Column(nullable = false,length = 255)
    private String imageUrl;

    @Column(nullable = false,length = 10)
    private String portion;


    @Column(nullable = false)
    private Boolean isActive;

    @Column(nullable = false)
    private Long categoryId;

}
