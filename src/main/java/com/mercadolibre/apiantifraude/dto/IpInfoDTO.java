package com.mercadolibre.apiantifraude.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

/**
 * DTO para respuesta de peticion de informacion de IP
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class IpInfoDTO {
    private String adress;
    private String country;
    private String isoCode;
    private Map<String, String> currenciesExchange;
}
