package com.sencerseven.basittarifler.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "USERS_DETAIL")
public class UsersDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Column(name = "profile_img")
    private String profileImg;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    Users users;

    public UsersDetail() {
    }

    public UsersDetail(String description, String profileImg, Users users) {
        this.description = description;
        this.profileImg = profileImg;
        this.users = users;
    }
}
