package com.jungwoon.simple_notice_board.domain;

import com.jungwoon.simple_notice_board.domain.likes.Likes;
import com.jungwoon.simple_notice_board.domain.likes.LikesRepository;
import com.jungwoon.simple_notice_board.domain.post.Post;
import com.jungwoon.simple_notice_board.domain.post.PostRepository;
import com.jungwoon.simple_notice_board.domain.test_tool.Repositories;
import com.jungwoon.simple_notice_board.domain.user.Gender;
import com.jungwoon.simple_notice_board.domain.user.User;
import com.jungwoon.simple_notice_board.domain.user.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class LikesRepositoryTest {

    @Autowired
    LikesRepository likesRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    Repositories repositories;

    Post post;
    User user;

    LocalDateTime now;

    // 테스트에 필요한 필드 초기화
    @BeforeEach
    void setData() {
        now = LocalDateTime.now().minusSeconds(10L);

        repositories.deleteDataOfAllTables();
        repositories.setDummyUsers();

        user = userRepository.findAll().get(0);

        post = Post.builder()
                .title("제목")
                .author(user)
                .content("내용")
                .attachedFile("첨부파일")
                .build();
    }

    // 외래키가 잘 설정 되었는지 테스트
    @Test
    public void foreignKeyTest() {
        assertThrows(InvalidDataAccessApiUsageException.class,
                () -> likesRepository.save(Likes.builder()
                .post(post)
                .user(user)
                .build())
        );
    }

    // 저장 테스트
    @Test
    public void saveTest() {
        // given
        postRepository.save(post);

        List<User> userList = userRepository.findAll();
        List<Post> postList = postRepository.findAll();

        // when
        likesRepository.save(Likes.builder()
                .post(postList.get(0))
                .user(userList.get(0))
                .build());

        Long count = likesRepository.count();

        // then
        assertThat(count).isEqualTo(1);
    }

    @Test
    public void createdAtTest() {

        postRepository.save(post);

        // when
        likesRepository.save(Likes.builder()
                .post(post)
                .user(user)
                .build());

        Likes like = likesRepository.findAll().get(0);

        // then
        System.out.println(" >>> CreatedAt : " + like.getCreatedAt());
        assertThat(like.getCreatedAt()).isAfter(now);
    }
}
