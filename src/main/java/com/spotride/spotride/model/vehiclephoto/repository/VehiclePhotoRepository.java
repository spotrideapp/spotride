package com.spotride.spotride.model.vehiclephoto.repository;

import com.spotride.spotride.model.vehiclephoto.model.VehiclePhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Implementation {@link JpaRepository} for {@link VehiclePhoto}.
 */
@Repository
public interface VehiclePhotoRepository extends JpaRepository<VehiclePhoto, Long> {
}
