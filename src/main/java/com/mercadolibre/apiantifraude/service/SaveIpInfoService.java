package com.mercadolibre.apiantifraude.service;

import com.mercadolibre.apiantifraude.dto.IpInfoDTO;
import com.mercadolibre.apiantifraude.model.Country;
import com.mercadolibre.apiantifraude.model.Currency;
import com.mercadolibre.apiantifraude.model.Ip;
import com.mercadolibre.apiantifraude.repository.CountryRepository;
import com.mercadolibre.apiantifraude.repository.IpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaveIpInfoService {

    @Autowired
    private IpRepository ipRepository;

    @Autowired
    private CountryRepository countryRepository;

    /**
     * Guarda informacion de Ip si no existe en base de datos
     * Retorna true si la entidad es guardada
     * Retorna false si ya existe
     * @param ipInfoDTO
     */
    public boolean saveIpInfoIfNew(IpInfoDTO ipInfoDTO) {

        if (!ipRepository.existsByIp(ipInfoDTO.getAdress())){
            countryRepository.save(buildpersistanceData(ipInfoDTO));
            return true;
        } else
            return false;
    }

    /**
     * Guarda una ip y la marca como bloqueada
     * @param ipAdress
     */
    public Ip saveBlackListedIp(String ipAdress) {
        Ip ip = ipRepository.findByIp(ipAdress);
        if (ip==null) {
            ip = new Ip();
            ip.setAdress(ipAdress);
        }
        ip.setActive(false);
        return ipRepository.save(ip);
    }

    /**
     * Construye el objeto Country y sus objetos child (Ip, Currency) para persistencia
     * @param ipInfoDTO
     * @return
     */
    private Country buildpersistanceData(IpInfoDTO ipInfoDTO) {
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

        return country;
    }
}
