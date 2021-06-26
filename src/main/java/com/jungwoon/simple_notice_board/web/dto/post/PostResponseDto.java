package com.jungwoon.simple_notice_board.web.dto.post;

import com.jungwoon.simple_notice_board.domain.posts.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class PostResponseDto {
    private Long id;
    private String title;
    private String author;
    private String content;
    private String attachedFile;
    private long visit;
    private long likes;

    public void PostResponseDto(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor().getEmail();
        this.content = entity.getContent();
        this.attachedFile = entity.getAttachedFile();
    }
}
