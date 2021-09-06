package com.example.patient.services;

import com.example.patient.entities.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {

    private UserEntity user;

    public UserDetailsImpl(UserEntity user) {
        this.user = user;
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final List<GrantedAuthority> authorities = new ArrayList<>();
//        System.out.println(user.getRoles());
//        authorities.add(new SimpleGrantedAuthority("ROLE_Admin"));
        authorities.add(new SimpleGrantedAuthority(user.getRoles()));
        authorities.forEach(System.out::println);
        return authorities;
    }

    // l'exemple dans le cours où on a une table role et une relation @ManyToMany
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//        for (final Role role : user.getRoles())
//            authorities.add(new SimpleGrantedAuthority(role.getTitre()));
//        return authorities;
//    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
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
        return true;
    }

}
