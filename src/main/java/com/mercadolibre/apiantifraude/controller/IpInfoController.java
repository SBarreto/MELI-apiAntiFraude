package com.mercadolibre.apiantifraude.controller;

import com.mercadolibre.apiantifraude.client.CountriesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IpInfoController {

    @Autowired
    private CountriesClient countriesClient;

    @GetMapping("meli/api/ipinfo")
    void getIpInfo() {
        countriesClient.getCountry();
    }

}
