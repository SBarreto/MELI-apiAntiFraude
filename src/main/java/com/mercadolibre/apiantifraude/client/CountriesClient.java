package com.mercadolibre.apiantifraude.client;

import com.mercadolibre.apiantifraude.config.ConfigBinder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URL;

@NoArgsConstructor
@Service
public class CountriesClient {

    @Autowired
    private ConfigBinder configBinder;
    private RestTemplate restTemplate;
    private String url;

    @Autowired
    public CountriesClient (RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public void getCountry() {
        //String url = "https://restcountries.com/v3.1/name/peru";
        Object object = restTemplate.getForObject(configBinder.getUrlCountries()+"name/peru", Object.class);
        System.out.println(object.toString());
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
