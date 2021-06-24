package com.jungwoon.simple_notice_board.domain.likes;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
}
