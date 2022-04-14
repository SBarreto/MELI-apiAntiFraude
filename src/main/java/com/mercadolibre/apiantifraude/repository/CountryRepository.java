package com.mercadolibre.apiantifraude.repository;

import com.mercadolibre.apiantifraude.model.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, Long> {
}
