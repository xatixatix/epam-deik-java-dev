package com.epam.training.ticketservice.core.user.repository;

import com.epam.training.ticketservice.core.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByNameAndPassword(String username, String password);
}
