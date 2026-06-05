package com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.out.security;

import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class UserDetailsAdapter implements UserDetails {

    private final Student student;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(student.getRole().toString()));
    }

    @Override
    public String getPassword() {
        return student.getPassword().value();
    }

    @Override
    public String getUsername() {
        return student.getEmail().value();
    }

    public String getId() {
        return student.getId().toString();
    }

}
