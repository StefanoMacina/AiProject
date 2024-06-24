package com.stenfra.GymAi.controller;

import com.stenfra.GymAi.Exceptions.InvalidRoleException;
import com.stenfra.GymAi.models.dtos.login.LoginRequest;
import com.stenfra.GymAi.models.dtos.login.LoginResponse;
import com.stenfra.GymAi.models.dtos.register.RegisterRequest;
import com.stenfra.GymAi.models.dtos.register.UserDto;
import com.stenfra.GymAi.models.entities.User;
import com.stenfra.GymAi.repositories.UserRepository;
import com.stenfra.GymAi.securityConfig.JwtService;
import com.stenfra.GymAi.service.AuthService;
import com.stenfra.GymAi.Exceptions.UserAlreadyExistsEx;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthUserController {

    @Autowired
    AuthService authService;

    @Autowired
    JwtService jwtService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> register(
            @Valid @RequestBody RegisterRequest registerRequest, BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("");
        }
        try {
            authService.signUp(registerRequest);
            UserDto resp = UserDto.builder()
                    .firstname(registerRequest.getFirstname())
                    .lastname(registerRequest.getLastname())
                    .email(registerRequest.getEmail())
                    .username(registerRequest.getUsername())
                    .role(registerRequest.getRole())
                    .build();
            return new ResponseEntity<>(resp, HttpStatus.CREATED);
        } catch (UserAlreadyExistsEx e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (InvalidRoleException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest loginRequest
    ) {
        try {
            LoginResponse loginResponse = authService.signIn(loginRequest);
            return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, loginResponse.getToken().toString()).body(loginResponse);
        } catch (BadCredentialsException | UsernameNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/signout")
    public ResponseEntity<?> signout() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(() -> new UsernameNotFoundException(""));
        ResponseCookie cookie = jwtService.getCleanJwtCookie();
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body("you've been signed out successfully %s %s !".formatted(user.getFirstname(), user.getLastname()));
    }

}
