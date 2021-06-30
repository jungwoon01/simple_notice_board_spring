package com.jungwoon.simple_notice_board.config.auth.dto;

import com.jungwoon.simple_notice_board.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.email = user.getEmail();
        this.picture = user.getProfileImg();
    }
}
