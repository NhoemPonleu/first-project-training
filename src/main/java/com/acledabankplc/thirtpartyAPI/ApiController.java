package com.acledabankplc.thirtpartyAPI;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/call")
public class ApiController {

    private final ApiService apiService;

    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }
    @PreAuthorize("hasAuthority('admin:read') or hasAuthority('management:read')")
    @GetMapping("/thirtparty")

    public String fetchFromApi2() {
        return apiService.callApi2WithRestTemplate();
    }
}