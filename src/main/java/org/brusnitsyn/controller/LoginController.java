package org.brusnitsyn.controller;

import jakarta.servlet.http.HttpSession;
import org.brusnitsyn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        // Используем сервис вместо репозитория
        boolean isAuthenticated = userService.checkAuthentication(username, password);

        if (isAuthenticated) {
            var user = userService.getUserByUsername(username);
            session.setAttribute("user", user);
            session.setAttribute("username", user.getUsername());
            session.setAttribute("userId", user.getId());
            return "tasks";
        } else {
            model.addAttribute("error", "Неверный логин или пароль");
            return "login";
        }
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email,
            @RequestParam String confirmPassword,
            Model model) {

        // Переносим базовые проверки в сервис
        try {
            boolean created = userService.registerUser(username, password, email, confirmPassword);

            if (created) {
                model.addAttribute("success", "Регистрация успешна! Теперь войдите.");
                return "login";
            } else {
                model.addAttribute("error", "Ошибка регистрации");
                return "register";
            }
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login";
    }
}