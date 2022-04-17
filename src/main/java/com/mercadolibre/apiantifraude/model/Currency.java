package com.mercadolibre.apiantifraude.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Currency {

    @Id
    @GeneratedValue(generator = "currency_generator")
    private long id;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    private String name;

    private String exchangeRate;

    private Date lastUpdate;
}
