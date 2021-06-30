package com.jungwoon.simple_notice_board.domain.users;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    USER("ROLE_USER", "사용자"), HOST("ROLE_HOST", "관리자");

    private final String key;
    private final String title;
}
