package controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ThemeController {

    @PostMapping("/change-theme")
    public String changeTheme(@RequestParam("theme") String theme, HttpServletResponse response) {
        Cookie cookie = new Cookie("theme_mode", theme);
        cookie.setMaxAge(30 * 24 * 60 * 60);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/";
    }

    @GetMapping("/")
    public String index(@CookieValue(name = "theme_mode", defaultValue = "light") String theme, Model model) {
        model.addAttribute("currentTheme", theme);
        return "index";
    }
}