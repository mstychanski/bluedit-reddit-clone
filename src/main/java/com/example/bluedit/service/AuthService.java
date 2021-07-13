package com.example.bluedit.service;

import com.example.bluedit.model.NotificationEmail;
import com.example.bluedit.model.User;
import com.example.bluedit.model.VerificationToken;
import com.example.bluedit.repository.UserRepository;
import com.example.bluedit.repository.VerificationTokenRepository;

import com.example.bluedit.controller.dto.RegisterRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {


    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;

    @Transactional
    public void signup(RegisterRequest registerRequest){
        User user = new User();
        user.setUsername((registerRequest.getUsername()));
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated(Instant.now());
        user.setEnabled(false);

        userRepository.save(user);

        String token = generateVerificationToken(user);
        mailService.sendMail(new NotificationEmail("Activate your account via email",
                user.getEmail(),
                "Thank You for signing up to Bluedit, " +
                        "please verify your account by clicking on the url below: " +
                        "http://localhost:8080/api/auth/accountVerification/" + token));
    }


    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        verificationTokenRepository.save(verificationToken);
        return token;
    }
}
