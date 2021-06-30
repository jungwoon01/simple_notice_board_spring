package com.jungwoon.simple_notice_board.domain.visit;

import com.jungwoon.simple_notice_board.domain.BaseTimeEntity;
import com.jungwoon.simple_notice_board.domain.post.Post;
import com.jungwoon.simple_notice_board.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// 게시물 방문 정보 엔티티
@Getter
@NoArgsConstructor
@Entity
public class Visit extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 방문 사용자
    @ManyToOne
    private User user;

    // 방문 게시물
    @ManyToOne
    private Post post;

    @Builder
    public Visit(User user, Post post) {
        this.user = user;
        this.post = post;
    }
}
