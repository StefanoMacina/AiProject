package com.stenfra.GymAi.models.dtos.register;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.stenfra.GymAi.models.entities.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegisterRequest {

    @NotBlank(message = "firstname is mandatory field")
    private String firstname;

    @NotBlank(message = "lastname is mandatory field")
    private String lastname;

    @NotBlank(message = "username is mandatory field")
    @Size(min = 5,max = 20)
    private String username;

    @NotBlank(message = "email is mandatory field")
    @Email
    private String email;

    @NotBlank(message = "password is mandatory field")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$",
            message = "password must contain at least 1 uppercase, 1 lowercase, 1 special character, 1 digit, min length must be 8 characters")
    private String password;

    @JsonDeserialize(using = RoleDeserializer.class)
    private Role role;

    private LocalDate birthdate;

    static class RoleDeserializer extends JsonDeserializer<Role> {

        @Override
        public Role deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
            String value = jsonParser.getText().toUpperCase();
            if (!value.startsWith("ROLE_")) {
                value = "ROLE_" + value;
            }
            return Role.valueOf(value);
        }
    }


}
