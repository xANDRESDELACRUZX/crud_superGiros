package com.msvc.orden.controller;

import com.msvc.orden.entity.OrdenEntity;
import com.msvc.orden.entity.Product;
import com.msvc.orden.service.IOrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orden")
public class OrdenController {

    @Autowired
    private IOrdenService ordenService;

    @GetMapping("product/{id}")
    public ResponseEntity<?> getProduct(@PathVariable int id){
        //OrdenEntity orden = new Product();
        //.setId(id);
       return  ordenService.add(id);
    }

}
