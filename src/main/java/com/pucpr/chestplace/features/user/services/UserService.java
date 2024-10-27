package com.pucpr.chestplace.features.user.services;

import org.springframework.stereotype.Service;
import com.pucpr.chestplace.features.user.dtos.UserGetResponseDTO;
import com.pucpr.chestplace.features.user.dtos.UserRegisterRequestDTO;
import com.pucpr.chestplace.features.user.dtos.UserUpdateRequestDTO;
import com.pucpr.chestplace.features.user.entities.User;
import com.pucpr.chestplace.features.user.exceptions.UserAlreadyRegisteredException;
import com.pucpr.chestplace.features.user.exceptions.UserNotFoundException;
import com.pucpr.chestplace.features.user.repositories.UserRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    // CREATE
    public boolean register(@Valid UserRegisterRequestDTO request) {

        boolean userExists = userRepository.existsByEmail(request.getEmail());

        if(userExists) throw new UserAlreadyRegisteredException(request.getEmail());
        
        var user = new User(
            request.getName(),
            request.getEmail(),
            request.getPassword(),
            request.getRole()
        );

        userRepository.save(user);
        
        return true;

    }

    // READ
    public List<UserGetResponseDTO> getAllUsers() {
        return userRepository.findAll()
                            .stream()
                            .map(UserGetResponseDTO::map)
                            .collect(Collectors.toList());
    }
    public UserGetResponseDTO getUserById(@NotNull Long id) {
        
        Optional<User> user = userRepository.findById(id);
        
        if(!user.isPresent()) throw new UserNotFoundException(id);
        
        return UserGetResponseDTO.map(user.get());

    }

    // UPDATE
    public boolean updateUser(@Valid UserUpdateRequestDTO updatedUser, Long userId) {

        Optional<User> userSearchResult = userRepository.findById(userId);

        if(userSearchResult.isEmpty()) throw new UserNotFoundException(userId);

        User user = userSearchResult.get();
        user.setName(updatedUser.getName());
        userRepository.save(user);

        return true;

    }

    // DELETE
    public void deleteUser(@NotNull Long id) {
        
        if(!userRepository.existsById(id)) throw new UserNotFoundException(id);

        userRepository.deleteById(id);

    }

}
