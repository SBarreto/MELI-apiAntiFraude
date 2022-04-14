package com.mercadolibre.apiantifraude.repository;

import com.mercadolibre.apiantifraude.model.Currency;
import org.springframework.data.repository.CrudRepository;

public interface CurrencyRepository extends CrudRepository<Currency, Long> {
}
