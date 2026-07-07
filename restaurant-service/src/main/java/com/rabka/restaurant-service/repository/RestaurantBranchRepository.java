package com.Rabka.rabka.repo;

import com.Rabka.rabka.entity.RestaurantBranch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantBranchRepository extends JpaRepository<RestaurantBranch, Long> {
}
