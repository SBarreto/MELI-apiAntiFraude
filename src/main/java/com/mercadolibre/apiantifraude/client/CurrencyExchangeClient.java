package com.mercadolibre.apiantifraude.client;

import com.mercadolibre.apiantifraude.client.response.CurrencyExchangeResponse;
import com.mercadolibre.apiantifraude.config.ConfigBinder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CurrencyExchangeClient {

    private final ConfigBinder configBinder;
    private final WebClient webClient;

    public CurrencyExchangeClient(ConfigBinder configBinder, WebClient.Builder webClientBuilder) {
        this.configBinder = configBinder;
        this.webClient = webClientBuilder.baseUrl(this.configBinder.getUrlCurrency()).build();
    }

    public CurrencyExchangeResponse getexChangeRateInfo(String currencies) {
        return webClient
                .get()
                .uri("/api/latest?access_key=&symbols=".concat(currencies))
                .retrieve()
                .bodyToMono(CurrencyExchangeResponse.class)
                .block();
    }
}
