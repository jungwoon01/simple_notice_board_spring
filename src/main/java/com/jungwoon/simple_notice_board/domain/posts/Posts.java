package com.jungwoon.simple_notice_board.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Posts {
    @Id // 해당 테이블의 PK 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK auto increment 를 위한 설정
    private Long id;

    private String title;

    private String author;

    private String content;

    private String attachedFile;

    @Builder
    public Posts(String title, String author, String content, String attachedFile) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.attachedFile = attachedFile;
    }
}
