package com.stenfra.GymAi.repository;

import com.stenfra.GymAi.models.dto.UserDto;
import com.stenfra.GymAi.models.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByusername(String username);
    UserEntity save(UserDto userDto);
}
