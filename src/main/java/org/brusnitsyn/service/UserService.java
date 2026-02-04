package org.brusnitsyn.service;

import org.brusnitsyn.model.User;
import org.brusnitsyn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public boolean registerUser(String username, String password, String email, String confirmPassword) {
        // Валидация
        if (!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("Пароли не совпадают");
        }

        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Пользователь уже существует");
        }

        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Некорректный email");
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setEmail(email);

        return userRepository.save(newUser);
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