package com.pucpr.chestplace.features.user.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserUpdateRequestDTO {

    private Long id;
    @NotBlank(message = "name is mandatory")
    @NotNull(message = "name is mandatory")
    private String name;

}
