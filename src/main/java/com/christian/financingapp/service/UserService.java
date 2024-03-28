package com.christian.financingapp.service;

import com.christian.financingapp.domain.User;
import com.christian.financingapp.domain.enumeration.UserRole;
import com.christian.financingapp.exception.EmailAlreadyExistsException;
import com.christian.financingapp.repository.UserRepository;
import com.christian.financingapp.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public User create(User user) {
        Optional<User> optionalUser = repository.findByEmail(user.getEmail());

        if (optionalUser.isPresent()) {
            throw new EmailAlreadyExistsException();
        }

        user.setRole(UserRole.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    public User getLoggedUser() {
        String loggedUserEmail = SecurityUtils.getCurrentUserEmail()
                .orElseThrow();

        return repository.findByEmail(loggedUserEmail)
                .orElseThrow();
    }
}
