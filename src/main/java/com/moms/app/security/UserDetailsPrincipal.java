package com.moms.app.security;

import com.moms.app.persistence.entity.UserEntity;

import java.time.LocalDate;
import java.util.Collection;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@AllArgsConstructor
public class UserDetailsPrincipal implements UserDetails {

    private UserEntity user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    public String getFirstName(){
        return this.user.getFirstName();
    }

    public String getLastName(){
        return this.user.getLastName();
    }

    public String getEmail(){
        return this.user.getEmail();
    }

    public LocalDate getDateOfBirth(){
        return this.user.getDateOfBirth();
    }

    public String getLocation(){
        return this.user.getLocation();
    }

    public String getStreet(){
        return this.user.getStreet();
    }

    public Long getHouseNumber(){
        return this.user.getHouseNumber();
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
