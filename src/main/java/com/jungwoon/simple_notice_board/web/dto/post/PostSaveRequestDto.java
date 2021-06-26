package com.jungwoon.simple_notice_board.web.dto.post;

import com.jungwoon.simple_notice_board.domain.posts.Post;
import com.jungwoon.simple_notice_board.domain.users.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostSaveRequestDto {
    private String author;
    private String title;
    private String content;
    private String attachedFile;

    @Builder
    public PostSaveRequestDto(String author, String title, String content, String attachedFile) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.attachedFile = attachedFile;
    }

    public Post toEntity(User author) {
        return Post.builder()
                .author(author)
                .title(title)
                .content(content)
                .attachedFile(attachedFile)
                .build();
    }
}
