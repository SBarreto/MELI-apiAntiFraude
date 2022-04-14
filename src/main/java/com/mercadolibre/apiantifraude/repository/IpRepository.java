package com.mercadolibre.apiantifraude.repository;

import com.mercadolibre.apiantifraude.model.Ip;
import org.springframework.data.repository.CrudRepository;

public interface IpRepository extends CrudRepository<Ip, Long> {
}
