package com.github.aalexeen.topjava2.web;

import com.github.aalexeen.topjava2.model.Role;
import com.github.aalexeen.topjava2.model.User;
import lombok.Getter;
import lombok.ToString;
import org.springframework.lang.NonNull;

import java.util.Set;

@Getter
@ToString(of = "user")
public class AuthUser extends org.springframework.security.core.userdetails.User {

    private final User user;

    public AuthUser(@NonNull User user) {
        super(user.getEmail(), user.getPassword(), user.getRoles());
        this.user = user;
    }

    public int id() {
        return user.id();
    }

    public Set<Role> getRoles() {
        return user.getRoles();
    }
}