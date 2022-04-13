package com.mercadolibre.apiantifraude.service;

import com.mercadolibre.apiantifraude.client.CountriesClient;
import com.mercadolibre.apiantifraude.client.IpClient;
import com.mercadolibre.apiantifraude.client.response.CountryCurrencyResponse;
import com.mercadolibre.apiantifraude.client.response.IpResponse;
import com.mercadolibre.apiantifraude.dto.IpCountryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IpInfoService {


    @Autowired
    CountriesClient countriesClient;

    @Autowired
    IpClient ipClient;

    public IpCountryDTO getIpInfo(String ip) {
        IpResponse ipResponse = ipClient.getIpInfo(ip);
        CountryCurrencyResponse[] countryCurrencyResponses = countriesClient.getCountryInfo(ipResponse.getCountryName());
        return convertData(ip, ipResponse, countryCurrencyResponses[0]);
    }

    private IpCountryDTO convertData(String ip, IpResponse ipResponse, CountryCurrencyResponse countryCurrencyResponse) {
        IpCountryDTO ipCountryDTO = new IpCountryDTO();
        ipCountryDTO.setAdress(ip);
        ipCountryDTO.setCountry(ipResponse.getCountryName());
        ipCountryDTO.setIsoCode(ipResponse.getCountryCode());
        ipCountryDTO.setCurrencies(countryCurrencyResponse.getCurrencyNames());
        return ipCountryDTO;
    }


}
