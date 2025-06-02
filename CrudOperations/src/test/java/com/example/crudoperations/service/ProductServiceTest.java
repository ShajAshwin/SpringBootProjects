package com.example.crudoperations.service;

import com.example.crudoperations.entity.Product;
import com.example.crudoperations.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService service;

    @MockBean
    private ProductRepository repository;

//    @Test
//    void saveProduct() {
//        Product product = new Product();
////        product.setId(10);
////		product.setName("Mobiles");
////		product.setQuantity(2);
////		product.setPrice(20000);
////        productRepository.save(product);
//
//
//        System.out.println("RESULT = "+repository.findByName("shaj"));
//        System.out.println("RESULT 2 = " + repository.findById(12));
//        System.out.println("RESULT 3 = " + repository.findById(1));
//
////       assertNotNull(productRepository.findByName("Mobile"));
//
//        assertNotNull(repository.findById(1));
//
//
//    }



    @Test
    void saveProducts() {
        Product product = new Product(111,"TV",1,50000);
        Product product1 = new Product(222,"computer",1,60000);
        repository.save(product1);
        when(repository.save(product)).thenReturn(product);

        assertEquals(product,service.saveProduct(product));

    }

    @Test
    void getProducts() {
        when(repository.findAll()).thenReturn(Stream.of(new Product(1,"",1,2000),new Product(2,"ss",5,100)).collect(Collectors.toList()));
        assertEquals(2,service.getProducts().size());
    }

    @Test
    void getProductById() {
    }

    @Test
    void getProductByName() {
        String name ="Shaj";
     //   when(repository.findByName(name)).thenReturn(Stream.of(new Product(1,"Shaj",1,2000)).collect(Collectors.toList()));
        assertEquals("Shaj",service.getProductByName(name));
    }

    @Test
    void deleteProduct() {
        Product product1 = new Product(222,"computer",1,60000);
        repository.save(product1);
        System.out.println(repository.findAll());
        service.deleteProduct(222);
//        verify(repository,times(1)).delete(product1);

        assertEquals(null,repository.findByName("computer"));

    }

    @Test
    void updateProduct() {
    }
}