package com.pucpr.chestplace.features.user.dtos;

import com.pucpr.chestplace.features.user.enums.Role;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRegisterRequestDTO {
    
    @Valid
    @NotBlank(message = "name is mandatory")
    @NotNull(message = "name is mandatory")
    private String name;

    @NotBlank(message = "name is mandatory")
    @NotNull(message = "name is mandatory")
    @Pattern(regexp = "^(.+)@(.+)$", message = "email must be a valid email")
    private String email;

    @NotBlank(message = "name is mandatory")
    @NotNull(message = "name is mandatory")
    @Size(min = 5, message = "password must have at least 5 characters")
    private String password;

    private Role role;

}
