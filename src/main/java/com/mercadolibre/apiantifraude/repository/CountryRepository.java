package com.mercadolibre.apiantifraude.repository;

import com.mercadolibre.apiantifraude.model.Country;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, Long> {

    @Query("select c " +
            "from Ip i " +
            "join " +
            "i.country c " +
            "where i.adress =:ipAdress")
    public Country findCountryByIp(String ipAdress);
}
