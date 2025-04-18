package com.spotride.spotride.model.user.repository;

import com.spotride.spotride.model.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Implementation {@link JpaRepository} for {@link User}.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
