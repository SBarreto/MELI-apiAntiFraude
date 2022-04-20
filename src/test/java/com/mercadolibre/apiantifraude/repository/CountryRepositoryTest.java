package com.mercadolibre.apiantifraude.repository;

import com.mercadolibre.apiantifraude.model.Country;
import com.mercadolibre.apiantifraude.model.Ip;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;


class CountryRepositoryTest {

    @Autowired
    private CountryRepository countryRepositoryUnderTest;

    @Test
    void itShouldFindCountryByIp() {
        //given
        Country country = new Country(
                1,
                "Colombia",
                "CO",
                new ArrayList<>(
                        new Ip()
                ),
                new ArrayList<>()
        );
        //when
        //then
    }
}