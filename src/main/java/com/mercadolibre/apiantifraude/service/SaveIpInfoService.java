package com.mercadolibre.apiantifraude.service;

import com.mercadolibre.apiantifraude.dto.IpInfoDTO;
import com.mercadolibre.apiantifraude.model.Country;
import com.mercadolibre.apiantifraude.model.Currency;
import com.mercadolibre.apiantifraude.model.Ip;
import com.mercadolibre.apiantifraude.repository.CountryRepository;
import com.mercadolibre.apiantifraude.repository.CurrencyRepository;
import com.mercadolibre.apiantifraude.repository.IpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaveIpInfoService {

    @Autowired
    private IpRepository ipRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private CountryRepository countryRepository;

    public void saveNewIpInfo(IpInfoDTO ipInfoDTO) {
        Country country = new Country();
        Ip ip = new Ip();
        List<Currency> currencies = new ArrayList<>();
        ip.setAdress(ipInfoDTO.getAdress());
        ip.setActive(true);
        ip.setCountry(country);
        currencies.addAll(
                ipInfoDTO.getCurrenciesExchange().entrySet()
                .stream()
                .map(e -> {
                    Currency currency = new Currency();
                    currency.setName(e.getKey());
                    currency.setExchangeRate(e.getValue());
                    currency.setCountry(country);
                    return currency;
                }).collect(Collectors.toList()));
        country.setName(ipInfoDTO.getCountry());
        country.setIsoCode(ipInfoDTO.getIsoCode());
        country.setIpList(new ArrayList<>());
        country.getIpList().add(ip);
        country.setCurrencies(new ArrayList<>());
        country.getCurrencies().addAll(currencies);

        countryRepository.save(country);

    }

    public void saveBlackListedIpInfo() {

    }
}
