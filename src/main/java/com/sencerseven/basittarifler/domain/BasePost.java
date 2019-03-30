package com.sencerseven.basittarifler.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BasePost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Temporal(TemporalType.DATE)
    private Date createdAt;

    private String title;

    private String description;

}
