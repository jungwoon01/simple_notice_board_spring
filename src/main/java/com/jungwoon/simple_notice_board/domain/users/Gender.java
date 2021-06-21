package com.jungwoon.simple_notice_board.domain.users;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Gender {
    MALE("ROLE_MALE", "남자"),
    FEMALE("ROLE_FEMALE", "여자");

    private final String key;
    private final String title;
}
