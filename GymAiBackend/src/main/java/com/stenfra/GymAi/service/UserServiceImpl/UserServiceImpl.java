package com.stenfra.GymAi.service.UserServiceImpl;

import com.stenfra.GymAi.models.dto.UserDto;
import com.stenfra.GymAi.models.entity.UserEntity;
import com.stenfra.GymAi.repository.UserRepository;
import com.stenfra.GymAi.service.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByusername(username);
    }

    @Override
    public UserEntity save(UserDto userDto) {
        UserEntity mappedUserEntity = new UserEntity(userDto.getUsername(),
                passwordEncoder.encode(userDto.getPassword()),
                userDto.getFullname());
        return userRepository.save(mappedUserEntity);
    }
}
