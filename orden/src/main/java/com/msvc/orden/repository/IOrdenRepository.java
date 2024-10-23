package com.msvc.orden.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msvc.orden.entity.OrdenEntity;

public interface IOrdenRepository extends JpaRepository<OrdenEntity,Integer> {

}
