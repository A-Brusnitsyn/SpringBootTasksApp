package org.brusnitsyn.controller;

import jakarta.servlet.http.HttpSession;
import org.brusnitsyn.db.DatabaseConnection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TaskController {

    //@GetMapping("/tasks")
    //public String showTasks(HttpSession session, Model model) {
        //    // Проверяем авторизацию
        //    if (session.getAttribute("user") == null) {
            //        return "redirect:/login";
            //    }
        //
        //    Long userId = (Long) session.getAttribute("userId");
        //    List<String> tasks = new ArrayList<>();
        //
        //    // Получаем задачи из БД (простой пример)
        //    String sql = "SELECT title FROM tasks WHERE user_id = ?";
        //
        //    try (Connection conn = DatabaseConnection.getConnection();
                  //         PreparedStatement stmt = conn.prepareStatement(sql)) {
            //
            //        stmt.setLong(1, userId);
            //        ResultSet rs = stmt.executeQuery();
            //
            //        while (rs.next()) {
                //            tasks.add(rs.getString("title"));
                //        }
            //
            //    } catch (Exception e) {
            //        e.printStackTrace();
            //    }
        //
        //    model.addAttribute("tasks", tasks);
        //    model.addAttribute("username", session.getAttribute("username"));
        //
        //    return "tasks";
        //}
}