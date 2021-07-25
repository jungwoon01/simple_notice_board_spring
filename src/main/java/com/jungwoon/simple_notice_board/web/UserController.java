package com.jungwoon.simple_notice_board.web;

import com.jungwoon.simple_notice_board.config.auth.LoginUser;
import com.jungwoon.simple_notice_board.config.auth.dto.SessionUser;
import com.jungwoon.simple_notice_board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/user/list")
    public String userList(Model model, @LoginUser SessionUser user) {

        if (user != null) {
            model.addAttribute("userEmail", user.getEmail());
        }

        model.addAttribute("users", userService.findAll());

        return "user-list";
    }
}
