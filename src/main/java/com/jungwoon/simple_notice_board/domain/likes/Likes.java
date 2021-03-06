package com.jungwoon.simple_notice_board.domain.likes;

import com.jungwoon.simple_notice_board.domain.BaseTimeEntity;
import com.jungwoon.simple_notice_board.domain.post.Post;
import com.jungwoon.simple_notice_board.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// 게시물의 좋아요 Entity
@Getter
@NoArgsConstructor
@Entity
public class Likes extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 좋아요 누른 유저
    @ManyToOne
    private User user;

    // 좋아요 눌린 게시물
    @ManyToOne
    private Post post;

    @Builder
    public Likes(User user, Post post) {
        this.user = user;
        this.post = post;
    }
}
