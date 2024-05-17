package com.ucc.crudservice.controller;

import com.ucc.crudservice.model.entities.Product;
import com.ucc.crudservice.service.productService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/products")
@RequiredArgsConstructor

public class productController {

    private final productService productService;

    // get all
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProducts(){
        return this.productService.getProducts();
    }
    // post
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void newProduct(@RequestBody Product product){
        this.productService.addProduct(product);
    }
    // get one
    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProductById(@PathVariable Long id){
        return this.productService.getProductById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    // delete
    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long productId) {
        System.out.println(productId);
        this.productService.deleteProduct(productId);
    }
    // update
    @PutMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateProduct(@PathVariable Long productId, @RequestBody Product updatedProduct) {
        this.productService.updateProduct(productId, updatedProduct);
    }
}