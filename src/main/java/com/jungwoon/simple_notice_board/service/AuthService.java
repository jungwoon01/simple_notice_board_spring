package com.jungwoon.simple_notice_board.service;

import com.jungwoon.simple_notice_board.config.oauth.PrincipalDetails;
import com.jungwoon.simple_notice_board.domain.user.Role;
import com.jungwoon.simple_notice_board.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepository;

    public boolean isUser(PrincipalDetails principalDetails) {

        return principalDetails.getAuthorities().iterator().next().getAuthority().equals(Role.USER.getKey());
    }
}
