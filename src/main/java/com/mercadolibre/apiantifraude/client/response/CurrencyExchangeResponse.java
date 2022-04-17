package com.mercadolibre.apiantifraude.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CurrencyExchangeResponse {

    private Map<String, String> currencyRates;

    @JsonProperty("rates")
    private void  unpackCurrencyRates(Map<String, Object> rates) {
        currencyRates = new HashMap<>();
        currencyRates = rates.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> String.valueOf(e.getValue())));
    }


}
