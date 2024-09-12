package com.acledabankplc.thirtpartyAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequest productRequest) {
        Product savedProduct = productService.createProductAndSave(productRequest);
        return ResponseEntity.ok(savedProduct);
    }
}
