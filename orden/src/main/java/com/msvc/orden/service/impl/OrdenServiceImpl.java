package com.msvc.orden.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.msvc.orden.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.msvc.orden.entity.OrdenEntity;
import com.msvc.orden.repository.IOrdenRepository;
import com.msvc.orden.service.IOrdenService;

import reactor.core.publisher.Mono;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class OrdenServiceImpl implements IOrdenService {

    @Autowired
    private IOrdenRepository ordenRepository;

    @Autowired
    private WebClient.Builder client;


    //all
    @Override
    public List<OrdenEntity> getall(){
        return ordenRepository.findAll();
    }

    //byId
    @Override
    public OrdenEntity getById(int id){
        return ordenRepository.findById(id).orElse(null);
    }
    
    //add
    @Override
    public ResponseEntity<?> add(int id/*OrdenEntity ordenEntity*/){
        try {
            Map<String,Object> params = new HashMap<>();
            params.put("id",id/*ordenEntity.getUser()*/);
            
         return new ResponseEntity<>(client.build()
                .get()
                .uri("http://msvc-produc/product/product/{id}", params)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                /*.onStatus(status -> status.is4xxClientError(), clientResponse -> {
                    return clientResponse.bodyToMono(String.class)
                            .flatMap(responseBody -> Mono.error(new RuntimeException("Error 4xx: " + responseBody)));
                })
                .onStatus(status -> status.is5xxServerError(), clientResponse -> {
                    return clientResponse.bodyToMono(String.class)
                            .flatMap(responseBody -> Mono.error(new RuntimeException("Error 5xx: " + responseBody)));
                })*/
                .bodyToMono(Product.class)
                .block(),HttpStatus.OK);
            
            /*client.build()
                    .get()
                    .uri("http://msvc-produc/product/product/{id}",id)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(Product.class)
                    //map para pasar a  un hipotetico item
                    /*.bodyToFlux(OrdenEntity.class)
                    .collectList()
                    .block();*/
            //return "orden creada correctamente";
        } catch (Exception e) {
            return new ResponseEntity<>("error "+e.getMessage(),HttpStatus.BAD_REQUEST);
            //return  null;
        }
    }

    //find by empresa

    //find by user

}
