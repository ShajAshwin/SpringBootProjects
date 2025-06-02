package com.example.crudoperations.controller;


import com.example.crudoperations.entity.Product;
import com.example.crudoperations.exceptions.EmptyListException;
import com.example.crudoperations.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("/addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
    Product savedProduct =  service.saveProduct(product);
        return new ResponseEntity<Product>(savedProduct, HttpStatus.OK);
    }
    @PostMapping("/addProducts")
    public ResponseEntity<List<Product>> addProducts(@RequestBody List<Product> products){
        List<Product> saveProductsList = service.saveProducts(products);
        return new ResponseEntity<List<Product>>(saveProductsList, HttpStatus.OK);
    }
    @GetMapping("/products")
    public ResponseEntity<List<Product>> findAllProducts(){
        if(service.getProducts().isEmpty()){
            throw new EmptyListException();
        }
        List<Product> getAllProducts =service.getProducts();
        return new ResponseEntity<List<Product>>(getAllProducts,HttpStatus.CREATED);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable int id){

        log.info(String.valueOf(id));

        if (service.getProductById(id).equals(null)){
            throw new NullPointerException();
        }
            Product getById = service.getProductById(id);
            return new ResponseEntity<Product>(getById, HttpStatus.OK);
        }

    @GetMapping("/product/{name}")
    public ResponseEntity<Product> findProductById(@PathVariable String name){
        if(service.getProductByName(name).equals(null)){
            throw new NullPointerException();
        }
        Product getByName =service.getProductByName(name);
        return new ResponseEntity<Product>(getByName,HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product){
            Product updateProduct = service.updateProduct(product);
            return new ResponseEntity<Product>(updateProduct, HttpStatus.OK);

    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id){
        String deleteMessage =service.deleteProduct(id);
        return new ResponseEntity<String>(deleteMessage,HttpStatus.OK);
    }

}
