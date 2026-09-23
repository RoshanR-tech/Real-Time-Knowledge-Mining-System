package com.knowledgemining.backend.service;

import com.knowledgemining.backend.dto.LoginRequest;
import com.knowledgemining.backend.dto.LoginResponse;
import com.knowledgemining.backend.entity.User;
import com.knowledgemining.backend.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // REGISTER USER
    public User registerUser(User user) {

        String encodedPassword =
                passwordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }

    // GET ALL USERS
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    // LOGIN USER
    public LoginResponse login(LoginRequest request) {

        Optional<User> userOptional =
                userRepository.findByEmail(request.getEmail());

        // USER NOT FOUND
        if (userOptional.isEmpty()) {

            return new LoginResponse(
                    false,
                    null,
                    null,
                    null,
                    "Invalid email or password"
            );
        }

        User user = userOptional.get();

        // CHECK PASSWORD
        boolean passwordMatches =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword()
                );

        // WRONG PASSWORD
        if (!passwordMatches) {

            return new LoginResponse(
                    false,
                    null,
                    null,
                    null,
                    "Invalid email or password"
            );
        }

        // LOGIN SUCCESS
        return new LoginResponse(
                true,
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }
}