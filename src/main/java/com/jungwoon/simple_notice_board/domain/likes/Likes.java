package com.jungwoon.simple_notice_board.domain.likes;

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
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long UserId;

    private Long postId;

    @Builder
    public Likes(Long userId, Long postId) {
        UserId = userId;
        this.postId = postId;
    }
}
