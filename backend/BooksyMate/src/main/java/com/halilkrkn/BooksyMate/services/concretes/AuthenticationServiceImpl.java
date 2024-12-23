package com.halilkrkn.BooksyMate.services.concretes;

import com.halilkrkn.BooksyMate.entities.concretes.user.User;
import com.halilkrkn.BooksyMate.repositories.RoleRepository;
import com.halilkrkn.BooksyMate.repositories.UserRepository;
import com.halilkrkn.BooksyMate.services.abstracts.AuthenticationService;
import com.halilkrkn.BooksyMate.services.dto.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public void register(RegisterRequest request) {
        // Register the user
        var role = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new IllegalStateException("ROLE USER was not initialized"));

        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(List.of(role))
                .accountLocked(false)
                .enabled(false)
                .build();

        userRepository.save(user);
    }
}
