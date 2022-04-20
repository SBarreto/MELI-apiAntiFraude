package com.mercadolibre.apiantifraude.Strategy;

import com.mercadolibre.apiantifraude.dto.IpInfoDTO;
import com.mercadolibre.apiantifraude.model.Country;
import com.mercadolibre.apiantifraude.model.Currency;
import com.mercadolibre.apiantifraude.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class GetInfoFromRepo implements IGetIpInfoStrategy {


    @Autowired
    CountryRepository countryRepository;

    @Override
    public IpInfoDTO getIpInfo(String ip) {
        Country country = countryRepository.findCountryByIp(ip);
        System.out.println(country.getName());

        return buildResponse(country, ip);
    }

    private IpInfoDTO buildResponse(Country country, String ip) {
        IpInfoDTO ipInfoDTO = new IpInfoDTO();
        ipInfoDTO.setCountry(country.getName());
        ipInfoDTO.setAdress(ip);
        ipInfoDTO.setIsoCode(country.getIsoCode());
        ipInfoDTO.setCurrenciesExchange
                (country.getCurrencies().stream().collect(
                Collectors.toMap(Currency::getName, Currency::getExchangeRate)
        ));
        return ipInfoDTO;
    }
}
