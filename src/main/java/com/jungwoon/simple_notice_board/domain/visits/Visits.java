package com.jungwoon.simple_notice_board.domain.visits;

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
public class Visits {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long visitorId;

    private Long postId;

    @Builder
    public Visits(Long visitorId, Long postId) {
        this.visitorId = visitorId;
        this.postId = postId;
    }
}
