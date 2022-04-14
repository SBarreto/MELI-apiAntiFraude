package com.mercadolibre.apiantifraude.service;

import com.mercadolibre.apiantifraude.client.CountriesClient;
import com.mercadolibre.apiantifraude.client.CurrencyExchangeClient;
import com.mercadolibre.apiantifraude.client.IpClient;
import com.mercadolibre.apiantifraude.client.response.CountryCurrencyResponse;
import com.mercadolibre.apiantifraude.client.response.CurrencyExchangeResponse;
import com.mercadolibre.apiantifraude.client.response.IpResponse;
import com.mercadolibre.apiantifraude.dto.IpInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Clase encargada de elegir y traer la informacion de una IP
 */
@Service
public class IpInfoService {

    @Autowired
    IGetIpInfoStrategy getIpInfoStrategy;

    @Autowired
    CountriesClient countriesClient;

    @Autowired
    IpClient ipClient;

    @Autowired
    CurrencyExchangeClient currencyExchangeClient;

    public IpInfoDTO getIpInfo(String ip) {
        IpResponse ipResponse = ipClient.getIpInfo(ip);
        CountryCurrencyResponse[] countryCurrencyResponses = countriesClient.getCountryInfo(ipResponse.getCountryName());
        String currencyNames = String.join(",", countryCurrencyResponses[0].getCurrencyNames());
        CurrencyExchangeResponse currencyExchangeResponse = currencyExchangeClient.getexChangeRateInfo(currencyNames);
        return convertData(ip, ipResponse, countryCurrencyResponses[0], currencyExchangeResponse);
    }


    private IpInfoDTO convertData(String ip, IpResponse ipResponse, CountryCurrencyResponse countryCurrencyResponse, CurrencyExchangeResponse currencyExchangeResponse) {
        IpInfoDTO ipCountryDTO = new IpInfoDTO();
        ipCountryDTO.setAdress(ip);
        ipCountryDTO.setCountry(ipResponse.getCountryName());
        ipCountryDTO.setIsoCode(ipResponse.getCountryCode());
        ipCountryDTO.setCurrenciesExchange(currencyExchangeResponse.getCurrencyRates());
        return ipCountryDTO;
    }


}
