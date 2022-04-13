package com.mercadolibre.apiantifraude.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Clase de mapeo JSON para respuesta de servicio de paises,
 * solo nos interesa obtener los nombres de moneda del pais buscado.
 * Tener en cuenta que algunos paises pueden tener mas de una moneda
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CountryCurrencyResponse {

    private List<String> currencyNames;

    @JsonProperty("currencies")
    private void unpackCurrencyName(Map<String, Object> currencies) {
        currencyNames = new ArrayList<>();
        currencyNames.addAll(currencies.keySet());
    }
}
