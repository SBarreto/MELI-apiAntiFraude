package com.mercadolibre.apiantifraude.Strategy;

import com.mercadolibre.apiantifraude.client.CountriesClient;
import com.mercadolibre.apiantifraude.client.CurrencyExchangeClient;
import com.mercadolibre.apiantifraude.client.IpClient;
import com.mercadolibre.apiantifraude.client.response.CountryCurrencyResponse;
import com.mercadolibre.apiantifraude.client.response.CurrencyExchangeResponse;
import com.mercadolibre.apiantifraude.client.response.IpResponse;
import com.mercadolibre.apiantifraude.dto.IpInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class GetInfoFromClient implements IGetIpInfoStrategy {

    @Autowired
    CountriesClient countriesClient;

    @Autowired
    IpClient ipClient;

    @Autowired
    CurrencyExchangeClient currencyExchangeClient;

    @Override
    public IpInfoDTO getIpInfo(String ip) {
        IpResponse ipResponse = ipClient.getIpInfo(ip);
        CountryCurrencyResponse[] countryCurrencyResponses = countriesClient.getCountryInfo(ipResponse.getCountryName());
        String currencyNames = String.join(",", countryCurrencyResponses[0].getCurrencyNames());
        CurrencyExchangeResponse currencyExchangeResponse = currencyExchangeClient.getexChangeRateInfo(currencyNames);
        return buildResponse(ipResponse, currencyExchangeResponse);
    }


    private IpInfoDTO buildResponse(IpResponse ipResponse, CurrencyExchangeResponse currencyExchangeResponse) {
        IpInfoDTO ipInfoDTO = new IpInfoDTO();
        ipInfoDTO.setAdress(ipResponse.getIp());
        ipInfoDTO.setCountry(ipResponse.getCountryName());
        ipInfoDTO.setIsoCode(ipResponse.getCountryCode());
        ipInfoDTO.setCurrenciesExchange(currencyExchangeResponse.getCurrencyRates());
        return ipInfoDTO;
    }
}
