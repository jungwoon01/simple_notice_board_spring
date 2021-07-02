package com.jungwoon.simple_notice_board.web.controller;

import com.jungwoon.simple_notice_board.config.auth.dto.SessionUser;
import com.jungwoon.simple_notice_board.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final HttpSession httpSession;
    private final UserService userService;

    @GetMapping("/user/list")
    public String user_list(Model model) {

        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if (user != null) {
            model.addAttribute("userEmail", user.getEmail());
        }

        model.addAttribute("users", userService.findAll());

        return "user-list";
    }
}