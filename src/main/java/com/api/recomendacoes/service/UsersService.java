package com.api.recomendacoes.service;

import com.api.recomendacoes.domain.user.User;
import com.api.recomendacoes.domain.user.UserRequestDTO;
import com.api.recomendacoes.errors.UserAlreadyExistsException;
import com.api.recomendacoes.repositories.UserRepository;
import com.api.recomendacoes.util.PasswordUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
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

        // Checking if the user sent a profile picture. If they did, we upload it to S3
        if (userRequestDTO.profilePicture() != null) {
            String imagePath = this.uploadImageToS3(userRequestDTO.profilePicture());

            if (imagePath == null) {
                throw new RuntimeException("Failed to upload image");
            }

            newUser.setProfilePicture(imagePath);
        }

        newUser.setUsername(userRequestDTO.username());
        newUser.setEmail(userRequestDTO.email());
        newUser.setPassword(this.hashPassword(userRequestDTO.password())); // Encrypting the password
        newUser.setProfileDescription(userRequestDTO.profileDescription());

        System.out.println("User created: " + newUser);

        // Saving the user to the database
        this.userRepository.save(newUser);

        return newUser;
    }

    public String uploadImageToS3(MultipartFile profilePicture) {
        // Implement the logic to upload the image to S3
        // Return the URL of the uploaded image
        return "1";
        //return "https://s3.amazonaws.com/your-bucket/" + imagePath;
    }

    public String hashPassword(String password) {
        // Implement the logic to encrypt the password
        return PasswordUtil.hashPassword(password);
    }
}
