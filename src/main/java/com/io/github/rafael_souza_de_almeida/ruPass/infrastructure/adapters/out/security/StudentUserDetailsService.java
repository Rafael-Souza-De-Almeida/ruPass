package com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.out.security;

import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.valueobjects.Email;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentUserDetailsService implements UserDetailsService {

    private final StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String emailRawString) throws UsernameNotFoundException {
        return studentRepository.findByEmail(new Email(emailRawString))
                .map(UserDetailsAdapter::new)
                .orElseThrow(() -> new UsernameNotFoundException("Student not found"));
    }
}
