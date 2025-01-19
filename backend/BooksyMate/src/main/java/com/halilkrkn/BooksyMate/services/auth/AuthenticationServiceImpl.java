package com.halilkrkn.BooksyMate.services.auth;

import com.halilkrkn.BooksyMate.core.enums.EmailTemplateName;
import com.halilkrkn.BooksyMate.dto.request.auth.AuthenticateRequest;
import com.halilkrkn.BooksyMate.dto.request.auth.RegistrationRequest;
import com.halilkrkn.BooksyMate.dto.response.auth.AuthenticateResponse;
import com.halilkrkn.BooksyMate.entities.token.Token;
import com.halilkrkn.BooksyMate.entities.user.User;
import com.halilkrkn.BooksyMate.repositories.auth.RoleRepository;
import com.halilkrkn.BooksyMate.repositories.auth.TokenRepository;
import com.halilkrkn.BooksyMate.repositories.auth.UserRepository;
import com.halilkrkn.BooksyMate.security.jwt.JwtService;
import com.halilkrkn.BooksyMate.services.email.EmailService;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final EmailService emailService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Value("${application.mailing.frontend.activation-url}")
    private String activationUrl;

    @Override
    public void register(RegistrationRequest request) throws MessagingException {
        // Register the user
        var role = roleRepository.findByName("USER")
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
        sendValidationEmail(user);

    }

    @Override
    public AuthenticateResponse authenticate(AuthenticateRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword())
        );

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        var claims = new HashMap<String, Object>();
        claims.put("id", user.getId());
        claims.put("fullName", user.getFullName());
        claims.put("email", user.getEmail());

        var jwtToken = jwtService.generateToken(claims, user);
        return AuthenticateResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    @Transactional
    public void activateAccount(String token) throws MessagingException {
        var savedToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalStateException("Token not found. Invalid token."));

        if (LocalDateTime.now().isAfter(savedToken.getExpiresAt())) {
            sendValidationEmail(savedToken.getUser());
            throw new IllegalStateException("Activation token has expired. A new one has been sent to your email.");
        }

        var user = userRepository.findById(savedToken.getUser().getId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setEnabled(true);
        userRepository.save(user);
        savedToken.setValidatedAt(LocalDateTime.now());
    }

    private void sendValidationEmail(User user) throws MessagingException {
        var newToken = generateAndSaveActivationToken(user);
        // Send email
        emailService.sendEmail(
                user.getEmail(),
                user.getFullName(),
                activationUrl,
                newToken,
                EmailTemplateName.ACTIVATE_ACCOUNT,
                "Activate your account"
        );
    }

    private String generateAndSaveActivationToken(User user) {
        // Generate token
        String generatedToken = generateActivationToken(6);
        var token = Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();
        tokenRepository.save(token);
        return generatedToken;
    }

    private String generateActivationToken(int length) {
        // Generate token
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }
        return codeBuilder.toString();
    }
}
