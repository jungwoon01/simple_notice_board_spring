package com.jungwoon.simple_notice_board.domain;

import com.jungwoon.simple_notice_board.domain.likes.Likes;
import com.jungwoon.simple_notice_board.domain.likes.LikesRepository;
import com.jungwoon.simple_notice_board.domain.posts.Posts;
import com.jungwoon.simple_notice_board.domain.posts.PostsRepository;
import com.jungwoon.simple_notice_board.domain.users.Users;
import com.jungwoon.simple_notice_board.domain.users.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class LikesRepositoryTest {

    @Autowired
    LikesRepository likesRepository;

    @Autowired
    PostsRepository postsRepository;

    @Autowired
    UsersRepository usersRepository;

    Posts post;
    Users user;

    @BeforeEach
    public void setData() {
        post = Posts.builder()
                .title("제목")
                .author("작성자")
                .content("내용")
                .attachedFile("첨부파일")
                .build();

        user = Users.builder()
                .email("test@test.com")
                .gender("남자")
                .address("주소")
                .profileImg("프로필사진")
                .build();

        likesRepository.deleteAll();
    }

    @Test
    public void foreignKeyTest() {
        assertThrows(InvalidDataAccessApiUsageException.class,
                () -> likesRepository.save(Likes.builder()
                .posts(post)
                .users(user)
                .build()));
    }

    @Test
    public void saveTest() {
        // given
        postsRepository.save(post);
        usersRepository.save(user);

        // when
        likesRepository.save(Likes.builder()
                .posts(post)
                .users(user)
                .build());

        Long count = likesRepository.count();

        // then
        assertThat(count).isEqualTo(1);
    }
}
