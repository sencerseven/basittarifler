package com.sencerseven.basittarifler.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class BKod implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String bkod;

    private String bvalue;

    private String bdesc;

    public BKod(String bkod, String bvalue, String bdesc) {
        this.bkod = bkod;
        this.bvalue = bvalue;
        this.bdesc = bdesc;
    }
}
