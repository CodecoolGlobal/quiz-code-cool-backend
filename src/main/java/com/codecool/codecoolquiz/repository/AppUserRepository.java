package com.codecool.codecoolquiz.repository;

import com.codecool.codecoolquiz.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

    Optional<AppUser> findByName(String name);

}
