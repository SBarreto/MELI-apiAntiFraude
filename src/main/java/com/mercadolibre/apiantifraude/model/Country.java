package com.mercadolibre.apiantifraude.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Country {

    @Id
    @GeneratedValue(generator = "country_generator")
    private long id;

    private String name;

    private String isoCode;

    @OneToMany(mappedBy = "country", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<Ip> ipList;

    @OneToMany(mappedBy = "country", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<Currency> currencies;

}
