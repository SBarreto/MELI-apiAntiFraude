package com.mercadolibre.apiantifraude.client;

import com.mercadolibre.apiantifraude.client.response.IpResponse;
import com.mercadolibre.apiantifraude.config.ConfigBinder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Cliente encargado de buscar la informacion de pais y codigo ISO para una direccion IP
 */
@Service
public class IpClient {

    private final ConfigBinder configBinder;
    private final WebClient webClient;

    public IpClient(ConfigBinder configBinder, WebClient.Builder webClientBuilder) {
        this.configBinder = configBinder;
        this.webClient = webClientBuilder.baseUrl(configBinder.getUrlIp()).build();
    }

    public IpResponse getIpInfo(String ip) {
        return webClient
                .get()
                .uri(ip.concat("?access_key="))
                .retrieve()
                .bodyToMono(IpResponse.class)
                .block();
    }
}
