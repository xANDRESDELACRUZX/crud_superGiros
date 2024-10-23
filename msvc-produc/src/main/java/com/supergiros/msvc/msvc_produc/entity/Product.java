package com.supergiros.msvc.msvc_produc.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "producto")
@Data
@NoArgsConstructor //constructor vacio
@AllArgsConstructor //constructor con  todos los argumentos
@ToString
@EqualsAndHashCode
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private Double precio;
    private Integer clase_id;
    private Integer tercero_id;
    private Integer user_id;
    private Integer estado;
}
