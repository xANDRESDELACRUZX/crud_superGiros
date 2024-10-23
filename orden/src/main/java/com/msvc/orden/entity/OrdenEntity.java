package com.msvc.orden.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@Table(name = "orden")
@NoArgsConstructor //constructor vacio
@AllArgsConstructor //constructor con  todos los argumentos
@ToString
@EqualsAndHashCode
public class OrdenEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer subtotal;
    private Integer iva;
    @Column(name = "user_id")
    private Integer user;
    @Column(name = "tercero_id")
    private Integer empresa;

}
