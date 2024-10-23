package com.msvc.auth.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "\"user\"")
@Data // get y set
@NoArgsConstructor //constructor vacio
@AllArgsConstructor //constructor con  todos los argumentos
@ToString
@EqualsAndHashCode
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombres;
    private String apellidos;
    private String email;
    private String password;
    private String token;
}
