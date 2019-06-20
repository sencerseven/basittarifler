package com.sencerseven.basittarifler.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
@Table(name = "POSTS_IMAGES")
public class PostsImages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    private String description;

    @ManyToOne
    @JoinColumn(name = "posts_id")
    private Posts posts;

    public PostsImages(String url, String description, Posts posts) {
        this.url = url;
        this.description = description;
        this.posts = posts;
    }
}
