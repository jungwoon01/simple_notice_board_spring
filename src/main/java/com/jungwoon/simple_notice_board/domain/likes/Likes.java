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
    private Users Users;

    @ManyToOne
    private Posts posts;

    @Builder
    public Likes(com.jungwoon.simple_notice_board.domain.users.Users users, Posts posts) {
        Users = users;
        this.posts = posts;
    }
}
