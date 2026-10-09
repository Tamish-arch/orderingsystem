package com.tamish.orderingsystem.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tamish.orderingsystem.dto.AuthResponse;
import com.tamish.orderingsystem.dto.LoginRequest;
import com.tamish.orderingsystem.dto.RegisterRequest;
import com.tamish.orderingsystem.dto.UserResponse;
import com.tamish.orderingsystem.entity.AppUser;
import com.tamish.orderingsystem.enums.Role;
import com.tamish.orderingsystem.exceptions.ConflictException;
import com.tamish.orderingsystem.exceptions.InvalidCredentialsException;
import com.tamish.orderingsystem.repository.AppUserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {
 
	private AppUserRepository userRepo;
	private PasswordEncoder passwordEncoder;
	private JwtService jwtService;
	public UserResponse register(RegisterRequest request) {
		
        if (userRepo.existsByUsername(request.getUsername())) {
            throw new ConflictException("Username already taken");
        }
        AppUser user = new AppUser();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.CUSTOMER);
        AppUser saved = userRepo.save(user);
        return new UserResponse(saved.getId(), saved.getUsername(), saved.getRole());
    
		
	}

	public AuthResponse login(LoginRequest request) {
	    AppUser user = userRepo.findByUsername(request.getUsername())
	            .orElseThrow(() -> new InvalidCredentialsException("Invalid username or password"));
	    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
	        throw new InvalidCredentialsException("Invalid username or password");
	    }
	    String token = jwtService.generateToken(user);
	    return new AuthResponse(token, "Bearer", jwtService.getExpirationSeconds());
}
}
