package controller;

import dto.ShopDto;
import jakarta.servlet.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import java.util.*;

@Controller
@SessionAttributes("shopData") // Bài 4: Giữ data qua các bước
public class MainController {

    // --- BÀI 1: GIỎ HÀNG (SESSION) ---
    @PostMapping("/cart/add")
    public String addToCart(@RequestParam("productId") String productId, HttpSession session) {
        List<String> cart = (List<String>) session.getAttribute("myCart");
        if (cart == null) cart = new ArrayList<>();
        cart.add(productId);
        session.setAttribute("myCart", cart);
        return "redirect:/cart/checkout";
    }

    @GetMapping("/cart/checkout")
    public String checkout(HttpSession session, Model model) {
        List<String> cart = (List<String>) session.getAttribute("myCart");
        model.addAttribute("message", (cart == null || cart.isEmpty()) ? "Trống!" : "Có " + cart.size() + " món.");
        return "checkout";
    }

    // --- BÀI 2 & 3: COOKIE (GUEST & DARK MODE) ---
    @GetMapping("/home")
    public String home(@CookieValue(name = "guest_name", defaultValue = "Khách lạ") String name,
                       @CookieValue(name = "theme", defaultValue = "light") String theme, Model model) {
        model.addAttribute("msg", "Chào " + name);
        model.addAttribute("theme", theme);
        return "home";
    }

    @PostMapping("/change-theme")
    public String theme(@RequestParam("theme") String theme, HttpServletResponse response) {
        Cookie c = new Cookie("theme", theme);
        c.setMaxAge(30 * 24 * 60 * 60); // 30 ngày
        c.setHttpOnly(true); // Chống XSS
        response.addCookie(c);
        return "redirect:/home";
    }

    // --- BÀI 4: WIZARD FORM (SHOP REGISTER) ---
    @ModelAttribute("shopData")
    public ShopDto shopDto() { return new ShopDto(); }

    @GetMapping("/shop/step1")
    public String step1() { return "step1"; }

    @PostMapping("/shop/step1")
    public String postStep1(@ModelAttribute("shopData") ShopDto data) { return "redirect:/shop/step2"; }

    @GetMapping("/shop/step2")
    public String step2() { return "step2"; }

    @PostMapping("/shop/finish")
    public String finish(@ModelAttribute("shopData") ShopDto data, SessionStatus status) {
        System.out.println("Lưu DB Shop: " + data.getShopName());
        status.setComplete(); // Dọn dẹp RAM ngay sau khi xong
        return "redirect:/home";
    }
}