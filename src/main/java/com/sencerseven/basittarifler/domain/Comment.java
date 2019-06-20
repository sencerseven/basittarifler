package com.sencerseven.basittarifler.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@EqualsAndHashCode(exclude = {"users","recipe"})
@Entity
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @ManyToOne
    private Users users;

    @ManyToOne
    private Recipe recipe;

    public Comment() {
    }

    public Comment(String text,Users users) {
        this.text = text;
        this.users = users;
    }
}
