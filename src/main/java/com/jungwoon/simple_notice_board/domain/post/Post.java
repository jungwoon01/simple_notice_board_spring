package com.jungwoon.simple_notice_board.domain.post;

import com.jungwoon.simple_notice_board.domain.BaseTimeEntity;
import com.jungwoon.simple_notice_board.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// 게시물 엔티티
@Getter
@NoArgsConstructor
@Entity
public class Post extends BaseTimeEntity {
    @Id // 해당 테이블의 PK 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK auto increment 를 위한 설정
    private Long id;

    private String title;

    // 작성자
    @ManyToOne
    private User author;

    private String content;

    private String attachedFile;

    @Builder
    public Post(String title, User author, String content, String attachedFile) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.attachedFile = attachedFile;
    }

    public void update(String title, String content, String attachedFile) {
        this.title = title;
        this.content = content;
        this.attachedFile = attachedFile;
    }
}
