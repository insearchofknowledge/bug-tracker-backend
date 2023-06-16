package com.insearchofknowledge.bugTracker.auth;

import com.insearchofknowledge.bugTracker.configuration.JwtService;
import com.insearchofknowledge.bugTracker.developer.Developer;
import com.insearchofknowledge.bugTracker.developer.DeveloperRepository;
import com.insearchofknowledge.bugTracker.developer.Role;
import com.insearchofknowledge.bugTracker.developer.developerDto.AddDeveloperDto;
import com.insearchofknowledge.bugTracker.developer.developerMapper.AddDeveloperMapper;
import com.insearchofknowledge.bugTracker.token.Token;
import com.insearchofknowledge.bugTracker.token.TokenRepository;
import com.insearchofknowledge.bugTracker.token.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final DeveloperRepository developerRepository;
    private final AddDeveloperMapper addDeveloperMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;

    public AuthenticationResponse register(AddDeveloperDto addDeveloperDto) {
        Developer developer = addDeveloperMapper.map(addDeveloperDto);
        developer.setPassword(passwordEncoder.encode(developer.getPassword()));
        developer.setRole(Role.ROLE_DEVELOPER);

        Developer savedDeveloper = developerRepository.save(developer);
        String jwtToken = jwtService.generateToken(developer);
        saveUserToken(jwtToken, savedDeveloper);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );
        Developer developer = developerRepository.findByEmail(authenticationRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User with this email: " + authenticationRequest.getEmail() + " does not exist"));
        String jwtToken = jwtService.generateToken(developer);
        revokeAllUserTokens(developer);  // getting rid of all pre existing tokens
        saveUserToken(jwtToken, developer); // saving new token
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private void saveUserToken(String jwtToken, Developer developer) {
        Token token = Token.builder()
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .isRevoked(false)
                .isExpired(false)
                .developer(developer)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(Developer developer) {
        List<Token> validUserTokens = tokenRepository.findAllValidTokensByUser(developer.getId());
        if (validUserTokens.isEmpty()) {
            return;
        }
        validUserTokens.forEach(token -> {
            token.setRevoked(true);
            token.setExpired(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}
