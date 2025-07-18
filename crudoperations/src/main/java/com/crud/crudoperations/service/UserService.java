package com.crud.crudoperations.service;
import com.crud.crudoperations.model.User;
import com.crud.crudoperations.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.List;
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setMobile(updatedUser.getMobile());
            user.setAge(updatedUser.getAge());
            user.setPassword(updatedUser.getPassword());
            user.setDateOfBirth(updatedUser.getDateOfBirth());
            user.setAddress(updatedUser.getAddress());
            return userRepository.save(user);
        });
    }

    public boolean deleteUser(Long id) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public List<User> searchUsers(String name, String email, String mobile) {
        if (name != null && email != null) {
            return userRepository.findByNameContainingIgnoreCaseAndEmailContainingIgnoreCase(name, email);
        } else if (name != null) {
            return userRepository.findByNameContainingIgnoreCase(name);
        } else if (email != null) {
            return userRepository.findByEmailContainingIgnoreCase(email);
        } else if (mobile != null) {
            return userRepository.findByMobile(mobile);
        } else {
            return userRepository.findAll(); // Return all if no query params
        }
    }
    // UserService.java

    public List<User> searchUsers(String name, String email, int page, int limit, String sortBy, String order) {
        Sort.Direction direction = order.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(direction, sortBy));


        if (name != null && email != null) {
            return userRepository.findByNameContainingIgnoreCaseAndEmailContainingIgnoreCase(name, email, pageable).getContent();
        } else if (name != null) {
            return userRepository.findByNameContainingIgnoreCase(name, pageable).getContent();
        } else if (email != null) {
            return userRepository.findByEmailContainingIgnoreCase(email, pageable).getContent();
        } else {
            return userRepository.findAll(pageable).getContent(); // if no filters, return all paginated
        }
    }

}
