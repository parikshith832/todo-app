package com.todoapp.service.impl;

import com.todoapp.dto.RegisterDto;
import com.todoapp.entity.User;
import com.todoapp.repository.UserRepository;
import com.todoapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User register(RegisterDto registerDto) {
        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new IllegalArgumentException("Username is already taken");
        }
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new IllegalArgumentException("Email is already registered");
        }

        User user = User.builder()
                .fullName(registerDto.getFullName().trim())
                .username(registerDto.getUsername().trim())
                .email(registerDto.getEmail().trim().toLowerCase())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .build();

        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUsernameOrEmail(String usernameOrEmail) {
        String value = usernameOrEmail.trim();
        return userRepository.findByUsernameOrEmail(value, value.toLowerCase())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}
