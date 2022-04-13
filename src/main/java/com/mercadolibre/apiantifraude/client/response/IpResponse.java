package com.mercadolibre.apiantifraude.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase de mapeo JSON de servicio de busqueda de IP,
 * nos interesa saber el nombre del pais y su codigo ISO
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class IpResponse {

    @JsonProperty("country_name")
    private String countryName;

    @JsonProperty("country_code")
    private String countryCode;

}
