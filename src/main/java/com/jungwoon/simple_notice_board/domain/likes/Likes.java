package com.jungwoon.simple_notice_board.domain.likes;

import com.jungwoon.simple_notice_board.domain.posts.Posts;
import com.jungwoon.simple_notice_board.domain.users.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Users users;

    @ManyToOne
    private Posts posts;

    @Builder
    public Likes(Users users, Posts posts) {
        this.users = users;
        this.posts = posts;
    }
}
