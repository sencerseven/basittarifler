package com.sencerseven.basittarifler.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
public class UsersDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private String profileImg;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="user_id")
    Users users;

    public UsersDetail() {
    }

    public UsersDetail(String description, String profileImg, Users users) {
        this.description = description;
        this.profileImg = profileImg;
        this.users = users;
    }
}
