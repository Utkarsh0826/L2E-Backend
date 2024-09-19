package com.learntoearn.learntoearn.service;

import com.learntoearn.learntoearn.DTO.UserResponseDTO;
import com.learntoearn.learntoearn.model.User;
import com.learntoearn.learntoearn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) throws Exception {
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            throw new Exception("Password and Confirm Password do not match");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<UserResponseDTO> authenticateUser(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            User loggedInUser = user.get();
            UserResponseDTO userResponseDTO = new UserResponseDTO(
                    loggedInUser.getId(),
                    loggedInUser.getName(),
                    loggedInUser.getEmail(),
                    loggedInUser.getPhoneNumber(),
                    loggedInUser.getGender(),
                    loggedInUser.getExam(),
                    loggedInUser.getRole()
                    
            );
            return Optional.of(userResponseDTO);
        }
        return Optional.empty();
    }
}
