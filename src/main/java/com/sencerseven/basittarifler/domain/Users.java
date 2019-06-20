package com.sencerseven.basittarifler.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "USERS")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String userName;

    private String lastName;

    private int active;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "USERS_ROLE",joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "users",cascade = CascadeType.DETACH)
    private Set<Recipe> recipes = new HashSet<>();

    @OneToOne(mappedBy = "users" ,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "users_detail_id")
    UsersDetail usersDetail;

    @OneToMany(mappedBy = "users",fetch = FetchType.LAZY,cascade = CascadeType.DETACH)
    Set<Comment> comments = new HashSet<>();


    public Users() {
    }

    public Users(Users users) {
        this.active = users.active;
        this.email = users.email;
        this.roles = users.roles;
        this.password = users.password;
        this.userName = users.userName;
        this.lastName = users.lastName;
        this.id = users.id;
    }



    public Users addUserDetails(UsersDetail usersDetail){
        usersDetail.setUsers(this);
        this.usersDetail = usersDetail;
        return this;
    }

    public Users addComment(Comment comments){
        comments.setUsers(this);
        this.comments.add(comments);
        return this;
    }


}
