package com.goodhomes.authentication.service;

import com.goodhomes.authentication.dao.models.User;
import com.goodhomes.authentication.dao.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        logger.info("Request recieved to fetch all the users.");
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        logger.info("Request recieved to fetch all the user with userId [{}].",id);
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    public User createUser(User user) {
        logger.info("Request recieved to create user.");
        user.setCreatedDate(Instant.now().toString());
        UUID uuid= generateRandomUUID();
        user.setUserId(Math.abs(uuid.getMostSignificantBits() ^ uuid.getLeastSignificantBits()));
        return userRepository.save(user);
    }

    public User updateUser(Long id, User updatedUser) {
        logger.info("Request recieved to update user with userId [{}].",id);
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
            existingUser.setFailedLoginAttempts(updatedUser.getFailedLoginAttempts());
            existingUser.setIsAccountLocked(updatedUser.getIsAccountLocked());
            existingUser.setIsEmailVerified(updatedUser.getIsEmailVerified());
            existingUser.setIsPhoneNumberVerified(updatedUser.getIsPhoneNumberVerified());
            existingUser.setLastLogin(updatedUser.getLastLogin());
            existingUser.setUpdatedDate(Instant.now().toString());
            return userRepository.save(existingUser);
        } else {
            return null;
        }
    }

    public boolean deleteUser(Long id) {
        logger.info("Request recieved to delete user with userId [{}].",id);
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
            return true;
        } else {
            return false;
        }
    }

    public UUID generateRandomUUID() {
        return UUID.randomUUID();
    }
}
