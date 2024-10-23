package com.msvc.orden.service;

import com.msvc.orden.entity.OrdenEntity;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface IOrdenService {
    public List<OrdenEntity> getall();

    public OrdenEntity getById(int id);

    public ResponseEntity<?> add(int id/*OrdenEntity ordenEntity*/);
}
