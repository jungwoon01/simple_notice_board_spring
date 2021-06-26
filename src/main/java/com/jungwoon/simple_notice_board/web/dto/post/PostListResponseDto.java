package com.jungwoon.simple_notice_board.web.dto.post;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostListResponseDto {
    private final Long id;
    private final String title;
    private final String author;
    private final String content;
    private final long visit;
    private final long likes;
}
