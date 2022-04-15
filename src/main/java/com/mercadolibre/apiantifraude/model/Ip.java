package com.mercadolibre.apiantifraude.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Ip {

    @Id
    @GeneratedValue(generator = "ip_generator")
    private long id;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    private String adress;

    private boolean active;


}
