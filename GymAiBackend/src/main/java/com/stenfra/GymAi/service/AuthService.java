package com.stenfra.GymAi.service;

import com.stenfra.GymAi.models.dtos.login.LoginRequest;
import com.stenfra.GymAi.models.dtos.login.LoginResponse;
import com.stenfra.GymAi.models.dtos.register.RegisterRequest;
import com.stenfra.GymAi.models.dtos.register.UserDto;
import com.stenfra.GymAi.models.entities.Gender;
import com.stenfra.GymAi.models.entities.Role;
import com.stenfra.GymAi.models.entities.User;
import com.stenfra.GymAi.repositories.UserRepository;
import com.stenfra.GymAi.securityConfig.JwtService;
import com.stenfra.GymAi.Exceptions.UserAlreadyExistsEx;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.EnumSet;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @Transactional
    public void signUp(RegisterRequest registerRequest) throws UserAlreadyExistsEx {
        User user = new User(
                registerRequest.getFirstname(),
                registerRequest.getLastname(),
                registerRequest.getUsername(),
                registerRequest.getEmail(),
                registerRequest.getBirthdate(),
                encoder.encode(registerRequest.getPassword()),
                registerRequest.getRole(),
                registerRequest.getGender()
        );

        if(userRepository.existsByUsername(user.getUsername())){
            throw new UserAlreadyExistsEx("username %s already exists".formatted(user.getUsername()));
        }

        if(userRepository.existsByEmail(user.getEmail())){
            throw new UserAlreadyExistsEx("email %s already exists".formatted(user.getEmail()));
        }

        userRepository.save(user);
    }

    public LoginResponse signIn(LoginRequest loginRequest){
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        User existingUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("user %s does not exists".formatted(username)));

        // TO-DO : handle this exception in global exception handler
        if(!encoder.matches(password, existingUser.getPassword())){
            throw new BadCredentialsException("incorrect password for user %s".formatted(username));
        }

        UserDto user = UserDto.builder()
                .firstname(existingUser.getFirstname())
                .lastname(existingUser.getLastname())
                .email(existingUser.getEmail())
                .username(existingUser.getUsername())
                .role(existingUser.getRole())
                .build();

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        ResponseCookie responseCookie = jwtService.generateJwtCookie(userDetails);

        return LoginResponse.builder()
                .token(responseCookie)
                .userDto(user)
                .build();
    }

}
