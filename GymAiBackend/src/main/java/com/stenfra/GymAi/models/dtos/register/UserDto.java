package com.stenfra.GymAi.models.dtos.register;

import com.stenfra.GymAi.models.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String firstname, lastname, username, email;
    private Role role;
}
