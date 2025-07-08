package com.itachi.DemoSpringBoot.service;

import com.itachi.DemoSpringBoot.model.Product;
import com.itachi.DemoSpringBoot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> searchProduct(String keyword) {
        return productRepository.searchProduct(keyword);
    }

    public ResponseEntity<String> deleteProduct(int id) {
        Product product = getAllProducts().stream().filter(p -> p.getId() == id).findFirst().orElse(null);
        if (product!=null) {
            productRepository.deleteById(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> updateProduct(int id, Product product) {
        Optional<Product> existingProductOptional = productRepository.findById(id);
        if (existingProductOptional.isEmpty()){
            return new ResponseEntity<>("Product not found with id: " + id, HttpStatus.NOT_FOUND);
        }
        Product existingProduct = existingProductOptional.get();
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setBrand(product.getBrand());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setReleaseDate(product.getReleaseDate());
        existingProduct.setAvailability(product.isAvailability());
        existingProduct.setStockQuantity(product.getStockQuantity());
        productRepository.save(existingProduct);

        return new ResponseEntity<>("Product updated successfully", HttpStatus.OK);

    }
}
