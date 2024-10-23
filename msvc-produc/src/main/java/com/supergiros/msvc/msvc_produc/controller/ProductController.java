package com.supergiros.msvc.msvc_produc.controller;

import org.springframework.web.bind.annotation.*;

import com.supergiros.msvc.msvc_produc.service.IProductService;
import com.supergiros.msvc.msvc_produc.service.ISeguridadService;
import com.supergiros.msvc.msvc_produc.validaciones.ProductForm;
import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;


@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ISeguridadService seguridadService;


    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestHeader("x-Auth-Token") String authToken,
                                        @RequestHeader("userid") String userId,
                                        @Valid @RequestBody ProductForm productForm,
                                        BindingResult result) {
        var segirdad = seguridadService.validarToken(userId,authToken);

        if (!segirdad.get("user"))
          return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("usuario no encontrado");
        else if (!segirdad.get("validacion") ) {
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token no valido");
        }
        return productService.saveProduct(productForm, result);     
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateProduct(@Valid @RequestBody ProductForm productForm,
                                           BindingResult result,
                                           @RequestHeader("x-Auth-Token") String authToken,
                                           @RequestHeader("userid") String userId) {

        var segirdad = seguridadService.validarToken(userId,authToken);
        if (!segirdad.get("validacion") && !segirdad.get("user"))
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("usuario no encontrado");
        else if (!segirdad.get("validacion") ) {
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token no valido");
        }
        return productService.updateProduct( productForm, result);
    }

    @PostMapping("/updateStatus")
    public ResponseEntity<?> updateProduct(@RequestBody ProductForm productForm,
                                           @RequestHeader("x-Auth-Token") String authToken,
                                           @RequestHeader("userid") String userId) {

        var segirdad = seguridadService.validarToken(userId,authToken);
        if (!segirdad.get("validacion") && !segirdad.get("user"))
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("usuario no encontrado");
        else if (!segirdad.get("validacion") ) {
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token no valido");
        }
        return productService.updateStateProduct(productForm);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAll(@RequestHeader("x-Auth-Token") String authToken,@RequestHeader("Userid") String userId) {
  
        var segirdad = seguridadService.validarToken(userId,authToken);

        if (!segirdad.get("validacion") && !segirdad.get("user"))
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("usuario no encontrado");
        else if (!segirdad.get("validacion") ) {
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token no valido");
        }

        var lista = productService.listAllProduct();
        
        if (lista.isEmpty()) {
            return  new ResponseEntity<>("no hay informacion disponible ", HttpStatus.OK);
        }
        return new ResponseEntity<>(productService.listAllProduct(), HttpStatus.OK);
        
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable int id,
                                            @RequestHeader("x-Auth-Token") String authToken,
                                            @RequestHeader("userid") String userId) {

        var segirdad = seguridadService.validarToken(userId,authToken);
        if (!segirdad.get("validacion") && !segirdad.get("user"))
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("usuario no encontrado");
        else if (!segirdad.get("validacion") ) {
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token no valido");
        }

        var product = productService.buscarCliente(id);
        if (product == null)
            return  new ResponseEntity<>("no se encontro el producto " + id, HttpStatus.OK);

        Map<String, Object> response = new HashMap<>();
        response.put("valid", true);
        response.put("product", product);

        return new ResponseEntity<>(response, HttpStatus.OK);
        
    }
    
}
