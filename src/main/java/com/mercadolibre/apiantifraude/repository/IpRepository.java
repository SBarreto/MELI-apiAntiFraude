package com.mercadolibre.apiantifraude.repository;

import com.mercadolibre.apiantifraude.model.Ip;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IpRepository extends CrudRepository<Ip, Long> {

    @Query("SELECT CASE WHEN COUNT(i) > 0 THEN true ELSE false END FROM " +
            "Ip i where i.adress =:ipAdress")
    boolean existsByIp(String ipAdress);

    @Query("Select i from Ip i where i.country =:countryId")
    Ip findByCountryId(long countryId);

    @Query("SELECT CASE WHEN COUNT(i) > 0 THEN true ELSE false END FROM " +
            "Ip i where i.adress =:ipAdress and i.active=false")
    boolean isIpBlocked(String ipAdress);

}
