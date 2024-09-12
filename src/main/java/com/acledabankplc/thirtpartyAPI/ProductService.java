package com.acledabankplc.thirtpartyAPI;


import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {

    private final RestTemplate restTemplate;
    private final ProductRepository productRepository;

    public ProductService(RestTemplate restTemplate, ProductRepository productRepository) {
        this.restTemplate = restTemplate;
        this.productRepository = productRepository;
    }

    public Product createProductAndSave(ProductRequest productRequest) {
        // Call the third-party API
        ProductResponse productResponse = createProduct(productRequest);

        // Save the response to the database
        Product product = new Product();
        product.setId(productResponse.getId());
        product.setTitle(productResponse.getTitle());
        product.setPrice(productResponse.getPrice());
        product.setDescription(productResponse.getDescription());
        product.setImage(productResponse.getImage());
        product.setCategory(productResponse.getCategory());

        return productRepository.save(product);
    }

    private ProductResponse createProduct(ProductRequest productRequest) {
        String url = "https://fakestoreapi.com/products";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ProductRequest> requestEntity = new HttpEntity<>(productRequest, headers);

        ResponseEntity<ProductResponse> responseEntity =
                restTemplate.exchange(url, HttpMethod.POST, requestEntity, ProductResponse.class);

        return responseEntity.getBody();
    }
}
