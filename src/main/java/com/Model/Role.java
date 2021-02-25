package com.Model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import java.util.Set;

@Entity
public class Role implements GrantedAuthority {
    @Id
    private Long Id;
    private String name;
    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
    public Role() {}

    public Role(long l, String name) {
        this.Id = l;
        this.name = name;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}
