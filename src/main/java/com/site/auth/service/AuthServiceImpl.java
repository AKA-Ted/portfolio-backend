package com.site.auth.service;

import com.site.auth.dto.AuthResponseDto;
import com.site.auth.dto.LoginRequestDto;
import com.site.auth.model.User;
import com.site.auth.repository.UserRepository;
import com.site.auth.security.JwtTokenProvider;
import com.site.global.exception.ResourceConflictException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(AuthenticationManager authenticationManager, 
                           JwtTokenProvider jwtTokenProvider,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthResponseDto login(LoginRequestDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);
        return new AuthResponseDto(token);
    }

    @Override
    public AuthResponseDto register(LoginRequestDto registerDto) {
        if (userRepository.findByUsername(registerDto.getUsername()).isPresent()) {
            throw new ResourceConflictException("User", "username", registerDto.getUsername());
        }

        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        userRepository.save(user);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        registerDto.getUsername(),
                        registerDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);

        return new AuthResponseDto(token);
    }
}
