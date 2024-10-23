package com.supergiros.msvc.msvc_produc.service;


import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import java.util.List;
import com.supergiros.msvc.msvc_produc.entity.Product;
import com.supergiros.msvc.msvc_produc.validaciones.ProductForm;

public interface IProductService {

    public ResponseEntity<?> saveProduct(ProductForm productForm, BindingResult result);
    public ResponseEntity<?> updateProduct(ProductForm productForm, BindingResult result);
    public ResponseEntity<?> updateStateProduct(ProductForm productForm);
    public Product buscarCliente(Integer id);
    public List<Product> listAllProduct();
}
