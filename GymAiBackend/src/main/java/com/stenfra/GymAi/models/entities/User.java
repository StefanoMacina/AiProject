package com.stenfra.GymAi.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Il campo 'firstname' non può essere vuoto")
    @NotNull(message = "Il campo 'firstname' è obbligatorio")
    private String firstname;

    @NotBlank(message = "Il campo 'lastname' non può essere vuoto")
    @NotNull(message = "Il campo 'lastname' è obbligatorio")
    private String lastname;

    @NotBlank(message = "Il campo 'email' non può essere vuoto")
    @NotNull(message = "Il campo 'email' è obbligatorio")
    private String email;

    @NotBlank(message = "Il campo 'username' non può essere vuoto")
    @NotNull(message = "Il campo 'username' è obbligatorio")
    private String username;

    private LocalDate birthdate;
    private Integer age;

    @NotBlank(message = "Il campo 'passwd' non può essere vuoto")
    @NotNull(message = "Il campo 'passwd' è obbligatorio")
    private String passwd;

    @NotNull(message = "Il campo 'gender' è obbligatorio")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull(message = "Il campo 'role' è obbligatorio")
    @Enumerated(EnumType.STRING)
    private Role role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return passwd;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User(String firstname, String lastname, String username ,String email,
                LocalDate birthdate, String passwd, Role role, Gender gender) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.birthdate = birthdate;
        if(this.birthdate != null){
            this.age = Period.between(birthdate, LocalDate.now()).getYears();
        } else {
            this.age = null;
        }
        this.passwd = passwd;
        this.role = role;
        this.gender=gender;
    }
}

