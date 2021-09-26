package com.example.demo.security;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * user Detail implementation
 * used as getter and setter for user and roles object
 */
public class MyUserDetails implements UserDetails {

    private String userName;
    private String password;
    private Boolean isActive;
    private List<GrantedAuthority> authorities;

    public MyUserDetails(User user, List<Role> roles) {
        userName = user.getUserName();
        password = user.getPassword();
        isActive = user.isActive();
        authorities = roles.stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    public MyUserDetails() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }
}
