package com.rabka.orderservice.repository;

import com.rabka.orderservice.entity.Courier;
import com.rabka.orderservice.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierRepository extends JpaRepository<Courier, Long> {
}
