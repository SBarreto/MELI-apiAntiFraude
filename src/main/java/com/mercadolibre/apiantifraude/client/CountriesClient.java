package com.mercadolibre.apiantifraude.client;

import com.mercadolibre.apiantifraude.client.response.CountryCurrencyResponse;
import com.mercadolibre.apiantifraude.config.ConfigBinder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Cliente encargado de buscar la informacion de monedas para un pais
 */
@Service
public class CountriesClient {

//    private RestTemplate restTemplate;
    private final ConfigBinder configBinder;
    private final WebClient webClient;

    public CountriesClient(WebClient.Builder webClientBuilder, ConfigBinder configBinder) {
        this.configBinder = configBinder;
        this.webClient = webClientBuilder.baseUrl(this.configBinder.getUrlCountries()).build();
    }

    public CountryCurrencyResponse[] getCountryInfo(String country) {
       return webClient
                .get()
                .uri("/v3.1/name/".concat(country))
                .retrieve()
                .bodyToMono(CountryCurrencyResponse[].class)
                .block();
    }
}
