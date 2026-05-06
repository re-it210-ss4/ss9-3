package controller;

import dto.ShopDto; // Bạn tự tạo class DTO với các trường tương ứng
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/shop")
@SessionAttributes("shopData") // Giữ đối tượng shopData trong Session
public class ShopRegisterController {

    @ModelAttribute("shopData")
    public ShopDto setUpShopDto() {
        return new ShopDto(); // Khởi tạo nếu chưa có
    }

    @GetMapping("/step1")
    public String step1() { return "step1"; }

    @PostMapping("/step1")
    public String postStep1(@ModelAttribute("shopData") ShopDto shopData) {
        return "redirect:/shop/step2";
    }

    @GetMapping("/step2")
    public String step2() { return "step2"; }

    @PostMapping("/finish")
    public String finish(@ModelAttribute("shopData") ShopDto shopData, SessionStatus status) {
        System.out.println("Đã lưu Shop: " + shopData.getShopName());

        status.setComplete();
        return "success";
    }
}