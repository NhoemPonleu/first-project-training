package com.acledabankplc.thirtpartyAPI;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Service

public class ApiService {

    private final RestTemplate restTemplate;
    @Value("${api.fakestore.url}")
    private String apiUrl;
    private final ObjectMapper objectMapper;
    public ApiService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public List<ThirtPartyResponse> callApi2WithRestTemplate() {
        String responseBody = restTemplate.getForObject(apiUrl, String.class);
        List<ThirtPartyResponse> thirtPartyResponses = null;
        try {
            thirtPartyResponses = objectMapper.readValue(responseBody, new TypeReference<List<ThirtPartyResponse>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return thirtPartyResponses;
    }
}
