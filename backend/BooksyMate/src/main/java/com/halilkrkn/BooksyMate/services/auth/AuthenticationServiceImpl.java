package com.halilkrkn.BooksyMate.services.auth;

import com.halilkrkn.BooksyMate.core.enums.EmailTemplateName;
import com.halilkrkn.BooksyMate.entities.token.Token;
import com.halilkrkn.BooksyMate.entities.user.User;
import com.halilkrkn.BooksyMate.repositories.RoleRepository;
import com.halilkrkn.BooksyMate.repositories.TokenRepository;
import com.halilkrkn.BooksyMate.repositories.UserRepository;
import com.halilkrkn.BooksyMate.services.email.EmailService;
import com.halilkrkn.BooksyMate.dto.request.RegistrationRequest;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final EmailService emailService;

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

        for(int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }
        return codeBuilder.toString();
    }
}
