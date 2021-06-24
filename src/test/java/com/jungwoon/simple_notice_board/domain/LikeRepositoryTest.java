package com.jungwoon.simple_notice_board.domain;

import com.jungwoon.simple_notice_board.domain.likes.Like;
import com.jungwoon.simple_notice_board.domain.likes.LikeRepository;
import com.jungwoon.simple_notice_board.domain.posts.Post;
import com.jungwoon.simple_notice_board.domain.posts.PostRepository;
import com.jungwoon.simple_notice_board.domain.users.Gender;
import com.jungwoon.simple_notice_board.domain.users.User;
import com.jungwoon.simple_notice_board.domain.users.UserRepository;
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
public class LikeRepositoryTest {

    @Autowired
    LikeRepository likeRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    Post post;
    User user;

    // 테스트에 필요한 필드 초기화
    @BeforeEach
    void setData() {
        user = User.builder()
                .email("test@test.com")
                .gender(Gender.MALE)
                .address("주소")
                .profileImg("프로필사진")
                .build();

        post = Post.builder()
                .title("제목")
                .author(user)
                .content("내용")
                .attachedFile("첨부파일")
                .build();
    }

    // 사용한 테이블 데이터 모두 지우기
    @AfterEach
    public void deleteData() {
        likeRepository.deleteAll();
        postRepository.deleteAll();
        userRepository.deleteAll();
    }

    @BeforeEach
    public void clearTables() {
        likeRepository.deleteAll();
        postRepository.deleteAll();
        userRepository.deleteAll();
    }

    // 외래키가 잘 설정 되었는지 테스트
    @Test
    public void foreignKeyTest() {
        assertThrows(InvalidDataAccessApiUsageException.class,
                () -> likeRepository.save(Like.builder()
                .post(post)
                .user(user)
                .build())
        );
    }

    // 저장 테스트
    @Test
    public void saveTest() {
        // given
        userRepository.save(user);
        postRepository.save(post);

        List<User> userList = userRepository.findAll();
        List<Post> postList = postRepository.findAll();

        // when
        likeRepository.save(Like.builder()
                .post(postList.get(0))
                .user(userList.get(0))
                .build());

        Long count = likeRepository.count();

        // then
        assertThat(count).isEqualTo(1);
    }

    @Test
    public void createdAtTest() {
        // given
        LocalDateTime now = LocalDateTime.now();

        userRepository.save(user);
        postRepository.save(post);

        // when
        likeRepository.save(Like.builder()
                .post(post)
                .user(user)
                .build());

        Like like = likeRepository.findAll().get(0);

        // then
        System.out.println(" >>> CreatedAt : " + like.getCreatedAt());
        assertThat(like.getCreatedAt()).isAfter(now);
    }
}
