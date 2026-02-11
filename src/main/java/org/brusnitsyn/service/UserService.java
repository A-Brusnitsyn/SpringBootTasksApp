package org.brusnitsyn.service;

import org.brusnitsyn.model.User;
import org.brusnitsyn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Регистрация пользователя с валидацией
     */
    public User registerUser(String username, String password, String email) throws Exception {
        // Проверка существования пользователя
        if (userRepository.existsByUsername(username)) {
            throw new Exception("Пользователь с таким именем уже существует");
        }

        // Создание пользователя
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setCreatedAt(LocalDateTime.now());

        userRepository.save(user);
        return user;
    }

    /**
     * Аутентификация
     */
    public boolean checkAuthentication(String username, String password) {
        return userRepository.existsByUsernameAndPassword(username, password);
    }

    /**
     * Получить пользователя по имени
     */
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Проверить существование пользователя
     */
    public boolean userExists(String username) {
        return userRepository.existsByUsername(username);
    }
}