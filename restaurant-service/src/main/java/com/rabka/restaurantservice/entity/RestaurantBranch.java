package com.Rabka.rabka.entity;

import com.rabka.restaurantservice.entity.Restaurant;
import com.rabka.restaurantservice.entity.WorkingHours;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

import javax.xml.stream.Location;
import java.util.List;


@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "restaurantBranch")
public class RestaurantBranch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Location location;

    @Column(nullable = false, length = 255)
    private String address;

    @Column(nullable = false, length = 10)
    @DecimalMin(value = "1.0",message = "reyting en azi 1 ola biler")
    @DecimalMax(value = "5.0", message = "reyting en cox 5 ola biler")
    private Double rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    private Boolean isActive;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "restaurant")
    private List<WorkingHours> workingHours;


}
