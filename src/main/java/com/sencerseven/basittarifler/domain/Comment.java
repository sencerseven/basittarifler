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
@Table(name = "COMMENT")
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    public Comment() {
    }

    public Comment(String text,Users users) {
        this.text = text;
        this.users = users;
    }
}
