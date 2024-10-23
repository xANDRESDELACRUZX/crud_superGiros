package com.supergiros.msvc.msvc_produc.repository;

import com.supergiros.msvc.msvc_produc.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserEntity,Integer> {
}
