package com.stenfra.GymAi.models.dtos.register;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.stenfra.GymAi.models.entities.Gender;
import com.stenfra.GymAi.models.entities.Role;
import jakarta.validation.constraints.*;
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

    @NotBlank(message = "invalid firstname : empty firstname")
    @NotNull(message = "invalid firstname : null firstname")
    private String firstname;

    @NotBlank(message = "invalid firstname : empty firstname")
    @NotNull(message = "Invalid lastname : null lastname")
    private String lastname;

    @NotBlank(message = "invalid firstname : empty username")
    @NotNull(message = "Invalid firstname : null firstname")
    @Size(min = 5, max = 20)
    private String username;

    @NotBlank(message = "invalid email : empty email")
    @NotNull(message = "Invalid email : null email")
    @Email
    private String email;

    @NotBlank(message = "invalid password : empty password")
    @NotNull(message = "Invalid password : null password")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$",
            message = "password must contain at least 1 uppercase, 1 lowercase, 1 special character, 1 digit, min length must be 8 characters")
    private String password;

    @NotNull(message = "Role is mandatory field")
    @JsonDeserialize(using = RoleDeserializer.class)
    private Role role;

    @Past(message = "birthdate must be in the past")
    private LocalDate birthdate;

    @NotNull(message = "Gender is a mandatory field")
    @JsonDeserialize(using = GenderDeserializer.class)
    private Gender gender;

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

    static class GenderDeserializer extends JsonDeserializer<Gender> {
        @Override
        public Gender deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
            String value = jsonParser.getText().toUpperCase();
            return Gender.valueOf(value);
        }


    }

}
