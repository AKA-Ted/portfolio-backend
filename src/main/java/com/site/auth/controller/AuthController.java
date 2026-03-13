package com.site.auth.controller;

import com.site.auth.dto.AuthResponseDto;
import com.site.auth.dto.LoginRequestDto;
import com.site.auth.service.AuthService;
import com.site.global.dto.GlobalResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<GlobalResponse<AuthResponseDto>> login(@Valid @RequestBody LoginRequestDto loginDto) {
        AuthResponseDto authResponse = authService.login(loginDto);
        return ResponseEntity.ok(GlobalResponse.success(authResponse));
    }

    @PostMapping("/register")
    public ResponseEntity<GlobalResponse<AuthResponseDto>> register(@Valid @RequestBody LoginRequestDto registerDto) {
        AuthResponseDto authResponse = authService.register(registerDto);
        return new ResponseEntity<>(GlobalResponse.created(authResponse), HttpStatus.CREATED);
    }
}
