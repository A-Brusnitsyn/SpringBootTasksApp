package org.brusnitsyn.controller;

import jakarta.servlet.http.HttpSession;
import org.brusnitsyn.db.DatabaseConnection;
import org.brusnitsyn.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {


    // Показать страницу логина
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    // Обработать логин
    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        // Находим пользователя в БД
        User user = DatabaseConnection.findUserByUsername(username);

        // Проверяем пароль
        if (user != null && user.getPassword().equals(password)) {
            // Успешный логин
            session.setAttribute("user", user);
            session.setAttribute("username", user.getUsername());
            session.setAttribute("userId", user.getId());

            return "redirect:/tasks"; // Перенаправляем на страницу задач
        } else {
            // Ошибка
            model.addAttribute("error", "Неверный логин или пароль");
            return "login";
        }
    }

    // Показать страницу регистрации
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    // Обработать регистрацию
    @PostMapping("/register")
    public String register(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email,
            @RequestParam String confirmPassword,
            Model model) {

        // Простые проверки
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Пароли не совпадают");
            return "register";
        }

        if (DatabaseConnection.userExists(username)) {
            model.addAttribute("error", "Пользователь уже существует");
            return "register";
        }

        // Создаём пользователя
        boolean created = DatabaseConnection.createUser(username, password, email);

        if (created) {
            model.addAttribute("success", "Регистрация успешна! Теперь войдите.");
            return "login";
        } else {
            model.addAttribute("error", "Ошибка регистрации");
            return "register";
        }
    }

    // Выйти из системы
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Уничтожаем сессию
        return "redirect:/login";
    }
}