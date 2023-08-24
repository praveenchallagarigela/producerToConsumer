package com.praveentechie.producertoconsumer.repository;

import com.praveentechie.producertoconsumer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public Optional<User> findByEmail(String email);

    public User findByName(String name);
}
