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
                    boolean matchesOfficial = true;
                    boolean matchesCommon = true;

                    // Check official filter
                    if (official != null && !official.isEmpty()) {
                        matchesOfficial = country.getName().getOfficial().toLowerCase().contains(official.toLowerCase());
                    }

                    // Check common filter
                    if (common != null && !common.isEmpty()) {
                        matchesCommon = country.getName().getCommon().toLowerCase().contains(common.toLowerCase());
                    }

                    // Return true only if both conditions are met (or if they are not set)
                    return matchesOfficial && matchesCommon;
                })
                .collect(Collectors.toList());
    }

}
