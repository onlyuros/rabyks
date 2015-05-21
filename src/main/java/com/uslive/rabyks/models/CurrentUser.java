package com.uslive.rabyks.models;

import org.springframework.security.core.authority.AuthorityUtils;

import com.uslive.rabyks.common.Role;
import com.uslive.rabyks.models.mysql.User;

public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public CurrentUser(User user) {
        super(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Long getId() {
        return user.getId();
    }

    public Role getRole() {
        return user.getRole();
    }

}
