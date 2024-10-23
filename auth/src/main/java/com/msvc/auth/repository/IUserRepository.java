package com.msvc.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.msvc.auth.entity.UserEntity;

public interface IUserRepository extends JpaRepository<UserEntity,Integer> {

       @Query(value = "SELECT * FROM \"user\" WHERE email = :email", nativeQuery = true)
    Optional<UserEntity> finByEmail(String email);

}
