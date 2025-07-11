package com.itachi.DemoSpringBoot.controller;

import com.itachi.DemoSpringBoot.model.Product;
import com.itachi.DemoSpringBoot.repository.dto.ProductRequestDTO;
import com.itachi.DemoSpringBoot.repository.dto.ProductResponseDTO;
import com.itachi.DemoSpringBoot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/products")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<String> addProduct(@RequestBody ProductRequestDTO product){
        productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product created successfully");
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<ProductResponseDTO>> searchProduct(@RequestParam(name = "keyword") String keyword){
        return new ResponseEntity<>(productService.searchProduct(keyword), HttpStatus.OK);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable(name = "id") int id){
        return productService.deleteProduct(id);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable(name = "id") int id, @RequestBody ProductRequestDTO product){
        return (productService.updateProduct(id, product));
    }
}
