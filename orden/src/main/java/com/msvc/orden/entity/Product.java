package com.msvc.orden.entity;

import lombok.*;

@Data
@NoArgsConstructor //constructor vacio
@AllArgsConstructor //constructor con  todos los argumentos
@ToString
@EqualsAndHashCode
public class Product {

    private Integer id;
    private String nombre;
    private Double precio;
    private Integer clase_id;
    private Integer tercero_id;
    private Integer user_id;
    private Integer estado;
}