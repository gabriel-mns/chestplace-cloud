package com.pucpr.chestplace.features.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pucpr.chestplace.features.user.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String username);

    boolean existsByEmail(String email);

    boolean existsById(Long id);

}
