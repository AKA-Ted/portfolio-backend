package com.site.auth.service;

import com.site.auth.dto.AuthResponseDto;
import com.site.auth.dto.LoginRequestDto;

public interface AuthService {
    AuthResponseDto login(LoginRequestDto loginDto);
    AuthResponseDto register(LoginRequestDto registerDto);
}
