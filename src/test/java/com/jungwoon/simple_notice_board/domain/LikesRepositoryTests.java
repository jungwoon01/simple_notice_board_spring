package com.jungwoon.simple_notice_board.domain;

import com.jungwoon.simple_notice_board.domain.posts.PostsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LikesRepositoryTests {

    @Autowired
    PostsRepository postsRepository;

    @Test
    public void saveLikeTest() {
        // given

    }
}
