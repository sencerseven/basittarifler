package com.sencerseven.basittarifler.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"postsImages"})
@Entity
@Table(name = "POSTS")
public class Posts implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "int default 0")
    private String title;

    @Column(columnDefinition = "int default 0")
    private int viewCount;

    @Temporal(TemporalType.DATE)
    private Date createdAt;

    private boolean active;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "posts",orphanRemoval = true)
    private Set<PostsImages> postsImages = new HashSet<>();

    public Posts(String title, int viewCount, Date createdAt, boolean active, Set<PostsImages> postsImages) {
        this.title = title;
        this.viewCount = viewCount;
        this.createdAt = createdAt;
        this.active = active;
        this.postsImages = postsImages;
    }

    public Posts addImages(PostsImages postsImages){
        postsImages.setPosts(this);
        this.getPostsImages().add(postsImages);
        return this;
    }
}
