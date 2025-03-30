package com.api.recomendacoes.service;

import com.api.recomendacoes.domain.user.User;
import com.api.recomendacoes.domain.user.UserRequestDTO;
import com.api.recomendacoes.errors.InvalidPasswordException;
import com.api.recomendacoes.errors.users.UserAlreadyExistsException;
import com.api.recomendacoes.repositories.UserRepository;
import com.api.recomendacoes.util.PasswordUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UsersService {

    private final UserRepository userRepository;

    @Transactional
    public User createUser(UserRequestDTO userRequestDTO) {

        // Checking if the user already exists in the database.
        // I did username and email validation separately to better user experience.
        Optional<User> existingUserUsername = this.userRepository.findByUsername(userRequestDTO.username());
        if (existingUserUsername.isPresent()) {
            throw new UserAlreadyExistsException("Username already exists");
        }

        Optional<User> existingUserEmail = this.userRepository.findByEmail(userRequestDTO.email());
        if (existingUserEmail.isPresent()) {
            throw new UserAlreadyExistsException("Email already exists");
        }

        User newUser = new User();
        newUser.setUsername(userRequestDTO.username());
        newUser.setEmail(userRequestDTO.email());
        newUser.setPassword(this.hashPassword(this.passwordValidation((userRequestDTO.password())))); // Encrypting the password
        newUser.setProfileDescription(userRequestDTO.profileDescription());
        newUser.setProfilePicture(userRequestDTO.profilePicture()); // Assuming this is a base64 string

        // Saving the user to the database
        this.userRepository.save(newUser);

        return newUser;
    }

    public String passwordValidation(String password){
        // Password must have at least 8 characters, one uppercase letter, one lowercase letter, and one digit
        if (password.length() < 8 || !password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")) {
            throw new InvalidPasswordException("Password must be at least 8 characters long, including one uppercase letter, one lowercase letter, and one digit");
        }
        return password;
    }

    public String hashPassword(String password) {
        // Implement the logic to encrypt the password
        return PasswordUtil.hashPassword(password);
    }
}
