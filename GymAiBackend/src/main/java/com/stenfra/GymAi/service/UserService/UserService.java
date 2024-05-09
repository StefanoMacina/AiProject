package com.stenfra.GymAi.service.UserService;

import com.stenfra.GymAi.models.dto.UserDto;
import com.stenfra.GymAi.models.entity.UserEntity;

public interface UserService {

    UserEntity findByUsername(String username);
    UserEntity save(UserDto userDto);

}
