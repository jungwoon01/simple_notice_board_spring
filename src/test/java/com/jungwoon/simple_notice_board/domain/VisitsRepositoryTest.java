package com.jungwoon.simple_notice_board.domain;

import com.jungwoon.simple_notice_board.domain.posts.Posts;
import com.jungwoon.simple_notice_board.domain.posts.PostsRepository;
import com.jungwoon.simple_notice_board.domain.users.Users;
import com.jungwoon.simple_notice_board.domain.users.UsersRepository;
import com.jungwoon.simple_notice_board.domain.visits.Visits;
import com.jungwoon.simple_notice_board.domain.visits.VisitsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class VisitsRepositoryTest {

    @Autowired
    VisitsRepository visitsRepository;

    @Autowired
    PostsRepository postsRepository;

    @Autowired
    UsersRepository usersRepository;

    Posts post;
    Users user;

    @BeforeEach
    void setData() {
        user = Users.builder()
                .email("test@test.com")
                .gender("남자")
                .address("주소")
                .profileImg("프로필사진")
                .build();

        post = Posts.builder()
                .title("제목")
                .user(user)
                .content("내용")
                .attachedFile("첨부파일")
                .build();
    }

    @AfterEach
    public void deleteData() {
        visitsRepository.deleteAll();
        postsRepository.deleteAll();
        usersRepository.deleteAll();
    }

    @Test
    public void foreignKeyTest() {
        assertThrows(InvalidDataAccessApiUsageException.class,
                () -> visitsRepository.save(Visits.builder()
                        .posts(post)
                        .users(user)
                        .build())
        );
    }

    @Test
    public void saveTest() {
        // given
        usersRepository.save(user);
        postsRepository.save(post);

        List<Users> usersList = usersRepository.findAll();
        List<Posts> postsList = postsRepository.findAll();

        // when
        visitsRepository.save(Visits.builder()
                .posts(postsList.get(0))
                .users(usersList.get(0))
                .build());

        Long count = visitsRepository.count();

        // then
        assertThat(count).isEqualTo(1);
    }
}
