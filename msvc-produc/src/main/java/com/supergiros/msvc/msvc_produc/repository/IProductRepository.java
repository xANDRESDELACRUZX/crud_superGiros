package com.supergiros.msvc.msvc_produc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supergiros.msvc.msvc_produc.entity.Product;

public interface IProductRepository extends JpaRepository<Product,Integer> {

}
