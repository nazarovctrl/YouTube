package com.example.youtube.service;

import com.example.youtube.config.security.CustomUserDetail;
import com.example.youtube.entity.ProfileEntity;
import com.example.youtube.repository.ProfileRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final ProfileRepository repository;

    public CustomUserDetailsService(ProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ProfileEntity> optional = repository.findByEmail(username);

        if (optional.isEmpty()) {
            throw new UsernameNotFoundException("Bad Cretensional");
        }
        ProfileEntity profile = optional.get();

        return new CustomUserDetail(profile);

    }
}
