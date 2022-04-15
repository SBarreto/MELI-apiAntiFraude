package com.mercadolibre.apiantifraude.Strategy;

import com.mercadolibre.apiantifraude.dto.IpInfoDTO;
import com.mercadolibre.apiantifraude.model.Country;
import com.mercadolibre.apiantifraude.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetInfoFromRepo implements IGetIpInfoStrategy {


    @Autowired
    CountryRepository countryRepository;

    @Override
    public IpInfoDTO getIpInfo(String ip) {
        Country country = countryRepository.findCountryByIp(ip);
        System.out.println(country.getName());
        return null;
    }

    private IpInfoDTO buildResponse() {
        return null;
    }
}
