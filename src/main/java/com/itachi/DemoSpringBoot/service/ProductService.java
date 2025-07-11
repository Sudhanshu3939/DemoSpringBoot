package com.itachi.DemoSpringBoot.service;

import com.itachi.DemoSpringBoot.model.Product;
import com.itachi.DemoSpringBoot.repository.ProductRepository;
import com.itachi.DemoSpringBoot.repository.dto.ProductRequestDTO;
import com.itachi.DemoSpringBoot.repository.dto.ProductResponseDTO;
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

    public List<ProductResponseDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::convertToResponseDTO).toList();
    }

    public void addProduct(ProductRequestDTO productRequestDTO) {
        Product product = new Product();
        product.setName(productRequestDTO.getName());
        product.setDescription(productRequestDTO.getDescription());
        product.setPrice(productRequestDTO.getPrice());
        product.setAvailability(productRequestDTO.isAvailability());
        productRepository.save(product);
    }

    public List<ProductResponseDTO> searchProduct(String keyword) {
        List<Product> products =  productRepository.searchProduct(keyword);
        return products.stream().map(this::convertToResponseDTO).toList();
    }

    public ResponseEntity<Void> deleteProduct(int id) {
        ProductResponseDTO product = getAllProducts().stream().filter(p -> p.getId() == id).findFirst().orElse(null);
        if (product!=null) {
            productRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> updateProduct(int id, ProductRequestDTO product) {
        Optional<Product> existingProductOptional = productRepository.findById(id);
        if (existingProductOptional.isEmpty()){
            return new ResponseEntity<>("Product not found with id: " + id, HttpStatus.NOT_FOUND);
        }
        Product existingProduct = existingProductOptional.get();
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setAvailability(product.isAvailability());
        productRepository.save(existingProduct);
        return new ResponseEntity<>("Product updated successfully", HttpStatus.OK);
    }

    private ProductResponseDTO convertToResponseDTO(Product product) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setAvailability(product.isAvailability());
        return dto;
    }

}
