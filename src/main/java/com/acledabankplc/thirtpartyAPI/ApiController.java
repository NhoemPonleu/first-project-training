package com.acledabankplc.thirtpartyAPI;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/call")
public class ApiController {

    private final ApiService apiService;

    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }
    @PreAuthorize("hasAuthority('admin:read') or hasAuthority('management:read')")
    @GetMapping("/thirtparty")
    public ResponseEntity<List<ThirtPartyResponse>> fetchFromApi2() {
        return ResponseEntity.ok(apiService.callApi2WithRestTemplate());
    }
}