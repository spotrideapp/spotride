package com.spotride.spotride.model.vehiclerecord.repository;

import com.spotride.spotride.model.vehiclerecord.model.VehicleRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Implementation {@link JpaRepository} for {@link VehicleRecord}.
 */
@Repository
public interface VehicleRecordRepository extends JpaRepository<VehicleRecord, Long> {
}
