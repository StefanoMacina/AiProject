package com.stenfra.GymAi.service;

import com.stenfra.GymAi.models.dtos.register.UserDto;
import com.stenfra.GymAi.models.entities.User;
import com.stenfra.GymAi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserDto getUserInfo(String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("user %s does not exists".formatted(username)));

        return UserDto.builder()
                .username(user.getUsername())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .gender(user.getGender())
                .build();

    };

}
