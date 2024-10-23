package com.supergiros.msvc.msvc_produc.validaciones;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductForm {

    @NotBlank (message = "El nombre es obligatorio")
    private String nombre;

    @NotNull(message = "El precio es requerido")
    private Double precio;

    @NotNull(message = "la clase es obligatoria")
    private Integer clase_id; ;
    
    private Integer tercero_id;
    private Integer user_id;
    private Integer estado;
    private Integer id;

    private String accion;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getClase_id() {
        return clase_id;
    }

    public void setClase_id(Integer clase_id) {
        this.clase_id = clase_id;
    }

    public Integer getTercero_id() {
        return tercero_id;
    }

    public void setTercero_id(Integer tercero_id) {
        this.tercero_id = tercero_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    

}
