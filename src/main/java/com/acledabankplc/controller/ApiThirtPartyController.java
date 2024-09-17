package com.acledabankplc.controller;

import com.acledabankplc.thirtpartyAPI.ApiService;
import com.acledabankplc.thirtpartyAPI.ThirtPartyResponse;
import com.acledabankplc.thirtpartyAPI.contries.Country;
import com.acledabankplc.thirtpartyAPI.contries.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/thirt-party")
public class ApiThirtPartyController {

    private final ApiService apiService;
    private final CountryService countryService;

    public ApiThirtPartyController(ApiService apiService, CountryService countryService) {
        this.apiService = apiService;
        this.countryService = countryService;
    }

    @PreAuthorize("hasAuthority('admin:read') or hasAuthority('management:read')")
    @GetMapping("/thirtparty")
    public ResponseEntity<List<ThirtPartyResponse>> fetchFromApi2() {
        return ResponseEntity.ok(apiService.callApi2WithRestTemplate());
    }

    @GetMapping("/countries")
    public List<Country> getCountries(
            @RequestParam(required = false) String official,
            @RequestParam(required = false) String common
    ) {
        return countryService.filterCountries(official, common);
    }
}