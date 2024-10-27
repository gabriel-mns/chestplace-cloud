package com.pucpr.chestplace.features.user.dtos;

import com.pucpr.chestplace.features.user.entities.User;
import com.pucpr.chestplace.features.user.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserGetResponseDTO {
    
    private Long id;
    private String name;
    private String email;
    private Role role;

    public static UserGetResponseDTO map(User user) {
        
        UserGetResponseDTO newUser = UserGetResponseDTO.builder()
                                                        .id(user.getId())
                                                        .name(user.getName())
                                                        .email(user.getEmail())
                                                        .role(user.getRole())
                                                        .build();

        return newUser;
    }

}
