package com.ucc.crudservice.service;

import com.ucc.crudservice.repositories.productRepository;
import com.ucc.crudservice.model.entities.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor


public class productService {

    private final productRepository productRepository;

    //metodo para obtener producto
    public List<Product> getProducts(){
        return  productRepository.findAll();
    }
    // metodo para obtener un producto
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
    //metodo para crear un producto
    public void addProduct (Product product){
        productRepository.save(product);
    }
    //metodo para borrar un producto
    @DeleteMapping
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    //metodo para actualizar
    public void updateProduct(Long productId, Product updatedProduct) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setSku(updatedProduct.getSku());
            product.setName(updatedProduct.getName());
            product.setDescription(updatedProduct.getDescription());
            product.setPrice(updatedProduct.getPrice());
            product.setStatus(updatedProduct.getStatus());
            // Actualiza otros campos según sea necesario
            productRepository.save(product);
        }
    }
}