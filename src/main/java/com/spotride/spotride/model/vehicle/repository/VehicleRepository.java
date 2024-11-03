package com.spotride.spotride.model.vehicle.repository;

import com.spotride.spotride.model.vehicle.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Implementation {@link JpaRepository} for {@link Vehicle}.
 */
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
