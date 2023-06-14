package com.proj.expensetrackerapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proj.expensetrackerapi.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Boolean existsByEmail(String email);
	Optional<User> findByEmail(String email);
}
