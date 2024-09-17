package com.acledabankplc.thirtpartyAPI.contries;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService {

    private static final String API_URL = "https://restcountries.com/v3.1/all?fields=name,flags";

    private final RestTemplate restTemplate;

    public CountryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Country> getCountries() {
        Country[] countries = restTemplate.getForObject(API_URL, Country[].class);
        return Arrays.asList(countries);
    }

    public List<Country> filterCountries(String official, String common) {
        List<Country> countries = getCountries();
        return countries.stream()
                .filter(country -> {
                    if (official != null && !official.isEmpty()) {
                        return country.getName().getOfficial().toLowerCase().contains(official.toLowerCase());
                    }
                    if (common != null && !common.isEmpty()) {
                        return country.getName().getCommon().toLowerCase().contains(common.toLowerCase());
                    }
                    return true;
                })
                .collect(Collectors.toList());
    }
}
