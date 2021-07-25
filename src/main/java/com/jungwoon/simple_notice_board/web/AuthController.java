package com.jungwoon.simple_notice_board.web;

import com.jungwoon.simple_notice_board.config.oauth.PrincipalDetails;
import com.jungwoon.simple_notice_board.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class AuthController {

    private final AuthService authService;

    @GetMapping("/auth/role")
    public String checkRole(@AuthenticationPrincipal PrincipalDetails principalDetails) {

        if(authService.isUser(principalDetails)){
            return "redirect:/";
        }

        return "sign-up";
    }
}
