package com.supergiros.msvc.msvc_produc.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import com.supergiros.msvc.msvc_produc.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import com.supergiros.msvc.msvc_produc.entity.Product;
import com.supergiros.msvc.msvc_produc.repository.IProductRepository;
import com.supergiros.msvc.msvc_produc.validaciones.ProductForm;


@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    
    //add /modificar // activo O desactivo
    @Override
    public ResponseEntity<?> saveProduct(ProductForm productForm, BindingResult result){

        if (result.hasErrors()) {
            // Mapa para almacenar errores
            Map<String, String> errores = new HashMap<>();

            // Recorrer los errores y añadirlos al mapa
            result.getFieldErrors().forEach(error ->
                    errores.put(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity.badRequest().body(errores);
        }
        
        //llenar el modelo user
        var product = new Product();
        product.setNombre(productForm.getNombre());
        product.setClase_id(productForm.getClase_id());
        product.setPrecio(productForm.getPrecio());
        product.setUser_id(productForm.getUser_id());
        product.setTercero_id(productForm.getTercero_id());
        product.setEstado(1);
        
        //crear producto
        productRepository.save(product);
        Map<String, Object> response = new HashMap<>();
        response.put("valid", true);
        response.put("msg", "producto creoado corectamente");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
    @Override
    public ResponseEntity<?> updateProduct(ProductForm productForm, BindingResult result){

        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            // Mapa para almacenar errores

            // Recorrer los errores y añadirlos al mapa
            result.getFieldErrors().forEach(error ->
                    response.put(error.getField(), error.getDefaultMessage())

            );
            response.put("valid", false);
            return ResponseEntity.badRequest().body(response);
        }
       
        //validar que exista el producto
        var productFind = this.buscarCliente(productForm.getId());
        if (productFind == null) {
            response.put("valid", false);
            response.put("msg", "prodcuto no encontrado "+productForm.getId());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        //llenar el modelo product
        var product = new Product();
        product.setNombre(productForm.getNombre());
        product.setClase_id(productForm.getClase_id());
        product.setPrecio(productForm.getPrecio());
        product.setUser_id(productForm.getUser_id());
        product.setTercero_id(productForm.getTercero_id());
        product.setId(productForm.getId());
        product.setEstado(productForm.getEstado());

        //modificar producto
        productRepository.save(product);
        response.put("valid", true);
        response.put("msg", "producto modificado corectamente");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateStateProduct( ProductForm productForm){
        Map<String, Object> response = new HashMap<>();

        //validar que exista el producto   
        var product = this.buscarCliente(productForm.getId());
        if (product == null) {
            response.put("valid", false);
            response.put("msg", "producto no encontrado" + productForm.getId());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        //llenar el modelo user
     
        product.setEstado(productForm.getAccion().equals("activar") ? 1 : 0);
    
        //modificar producto
        productRepository.save(product);
        var accion_response = productForm.getAccion().equals("activar") ? "producto activado": "producto desactivado";
        response.put("valid", true);
        response.put("msg", accion_response + " corectamente");
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    //list all
    @Override
    public List<Product> listAllProduct(){
        return productRepository.findAll();
    }


    //listByid
    @Override
    public Product buscarCliente(Integer id){

        return productRepository.findById(id).orElse(null);
    }

    //listar por empresa

    //listar por usuario

}
