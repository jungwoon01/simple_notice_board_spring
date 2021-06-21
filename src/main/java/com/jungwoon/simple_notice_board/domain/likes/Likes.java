package com.jungwoon.simple_notice_board.domain.likes;

import com.jungwoon.simple_notice_board.domain.posts.Posts;
import com.jungwoon.simple_notice_board.domain.users.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// 게시물의 좋아요 Entity
@Getter
@NoArgsConstructor
@Entity
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 좋아요 누른 유저
    @ManyToOne
    private Users users;

    // 좋아요 눌린 게시물
    @ManyToOne
    private Posts posts;

    @Builder
    public Likes(Users users, Posts posts) {
        this.users = users;
        this.posts = posts;
    }
}
