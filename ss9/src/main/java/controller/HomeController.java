package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String homePage(
            @CookieValue(name = "guest_name", defaultValue = "Khách lạ") String guestName,
            Model model) {
        model.addAttribute("msg", "Chào mừng " + guestName + (guestName.equals("Khách lạ") ? "" : " trở lại!"));
        return "home-page";
    }
}