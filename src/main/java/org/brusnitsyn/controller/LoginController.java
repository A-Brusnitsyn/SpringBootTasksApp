package org.brusnitsyn.controller;

import jakarta.validation.Valid;
import org.brusnitsyn.model.User;
import org.brusnitsyn.model.dto.LoginResponse;
import org.brusnitsyn.model.dto.RegisterRequest;
import org.brusnitsyn.model.dto.RegisterResponse;
import org.brusnitsyn.service.UserService;
import org.brusnitsyn.model.dto.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest loginRequest,
            HttpSession session) {

        boolean isAuthenticated = userService.checkAuthentication(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );

        if (isAuthenticated) {
            var user = userService.getUserByUsername(loginRequest.getUsername());

            session.setAttribute("user", user);
            session.setAttribute("username", user.getUsername());
            session.setAttribute("userId", user.getId());

            return ResponseEntity.ok(
                    new LoginResponse(true, "Вход выполнен успешно", user.getUsername(), user.getId())
            );
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse(false, "Неверный логин или пароль", null, null));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(
            @Valid @RequestBody RegisterRequest registerRequest,
            BindingResult bindingResult) {

        // 1. Валидация входных данных
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest()
                    .body(new RegisterResponse(false, errorMessage));
        }

        // 2. Проверка совпадения паролей
        if (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())) {
            return ResponseEntity.badRequest()
                    .body(new RegisterResponse(false, "Пароли не совпадают"));
        }

        try {
            // 3. Вызов сервиса (лучше возвращать User, а не boolean)
            User registeredUser = userService.registerUser(
                    registerRequest.getUsername(),
                    registerRequest.getPassword(),
                    registerRequest.getEmail()
            );

            // 4. Успешный ответ
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(RegisterResponse.success(
                            "Регистрация успешна",
                            registeredUser.getId(),
                            registeredUser.getUsername()
                    ));

        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(RegisterResponse.error("error"));

        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/check")
    public ResponseEntity<LoginResponse> checkAuth(HttpSession session) {
        String username = (String) session.getAttribute("username");
        Long userId = (Long) session.getAttribute("userId");

        if (username != null && userId != null) {
            return ResponseEntity.ok(
                    new LoginResponse(true, "Пользователь авторизован", username, userId)
            );
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse(false, "Пользователь не авторизован", null, null));
        }
    }
}