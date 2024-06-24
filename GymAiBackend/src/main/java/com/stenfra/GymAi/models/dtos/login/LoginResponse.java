package com.stenfra.GymAi.models.dtos.login;

import com.stenfra.GymAi.models.dtos.register.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseCookie;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {
    private UserDto userDto;
    private ResponseCookie token;
}
