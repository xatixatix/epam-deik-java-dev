package com.epam.training.ticketservice.core.user.repository;

import com.epam.training.ticketservice.core.user.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyUserRepository extends JpaRepository<MyUser, Integer> {

    Optional<MyUser> findByNameAndPassword(String username, String password);
}
