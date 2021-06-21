package com.jungwoon.simple_notice_board.domain.visits;

import com.jungwoon.simple_notice_board.domain.posts.Posts;
import com.jungwoon.simple_notice_board.domain.users.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// 게시물 방문 정보 엔티티
@Getter
@NoArgsConstructor
@Entity
public class Visits {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 방문 사용자
    @ManyToOne
    private Users users;

    // 방문 게시물
    @ManyToOne
    private Posts posts;

    @Builder
    public Visits(Users users, Posts posts) {
        this.users = users;
        this.posts = posts;
    }
}
