package com.mercadolibre.apiantifraude.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Country {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    private String isoCode;

    @OneToMany(mappedBy = "country")
    private List<Ip> ipList;

    @OneToMany(mappedBy = "country")
    private List<Currency> currencies;

}
