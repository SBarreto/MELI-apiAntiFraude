package com.mercadolibre.apiantifraude.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "endpoint")
public class ConfigBinder {

    private String urlCountries;
    private String urlIp;
    private String urlCurrency;

}
