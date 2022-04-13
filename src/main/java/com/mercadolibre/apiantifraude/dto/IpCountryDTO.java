package com.mercadolibre.apiantifraude.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * DTO para respuesta de peticion de informacion de IP
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class IpCountryDTO {
    private String adress;
    private String country;
    private String isoCode;
    private List<String> currencies;
}
