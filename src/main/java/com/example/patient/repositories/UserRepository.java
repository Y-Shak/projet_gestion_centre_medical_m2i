package com.example.patient.repositories;

import com.example.patient.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    public UserEntity findByEmailOrUsername(String email, String username);
}
