package com.example.Education.service.auth;

import com.example.Education.dto.CustomUserDetails;
import com.example.Education.exceptions.UserNotFoundException;
import com.example.Education.model.UserEntity;
import com.example.Education.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return buildCustomUserDetailsOfUsername(username);
    }

    private CustomUserDetails buildCustomUserDetailsOfUsername(String username) {
        UserEntity user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Incorrect Username"));

        return CustomUserDetails.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .fullName(user.getFullName())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole().getTitle())
                .password(user.getPassword())
                .build();
    }

}
