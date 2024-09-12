package com.acledabankplc.thirtpartyAPI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {

    private final RestTemplate restTemplate;
    @Value("${api.fakestore.url}")
    private String apiUrl;
    public ApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String callApi2WithRestTemplate() {
        return restTemplate.getForObject(apiUrl, String.class);
    }
}
