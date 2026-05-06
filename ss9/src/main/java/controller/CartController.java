package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @PostMapping("/add")
    public String addToCart(@RequestParam("productId") String productId, HttpSession session) {
        List<String> cart = (List<String>) session.getAttribute("myCart");
        if (cart == null) {
            cart = new ArrayList<>();
        }
        cart.add(productId);
        session.setAttribute("myCart", cart);
        return "redirect:/cart/checkout";
    }

    @GetMapping("/checkout")
    public String viewCheckout(HttpSession session, Model model) {
        List<String> cart = (List<String>) session.getAttribute("myCart");
        if (cart == null || cart.isEmpty()) {
            model.addAttribute("message", "Giỏ hàng của bạn đang trống!");
        } else {
            model.addAttribute("message", "Bạn có " + cart.size() + " sản phẩm.");
        }
        return "checkout-page";
    }
}