package com.jungwoon.simple_notice_board.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
