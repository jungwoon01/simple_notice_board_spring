package com.jungwoon.simple_notice_board.web.dto.post;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostUpdateRequestDto {
    private String title;
    private String content;
    private String attachedFile;
}
