package com.jungwoon.simple_notice_board.domain;

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

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    User user;

    // given
    String title = "제목";
    String content = "내용";
    String attachedFile = "첨부파일";

    // 테스트에 필요한 테이터 세팅
    @BeforeEach
    public void setUser() {
        user = User.builder()
                .email("이메일")
                .gender(Gender.MALE)
                .profileImg("사진")
                .address("주소")
                .build();

        userRepository.save(user);
    }

    // 사용한 테이블 데이터 모두 지우기
    @AfterEach
    public void deleteData() {
        postRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void get() {
        postRepository.save(Post.builder()
                .title(title)
                .author(user)
                .content(content)
                .attachedFile(attachedFile)
                .build()
        );

        // when
        List<Post> postList = postRepository.findAll();

        // then
        Post post = postList.get(0);
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getAuthor().getEmail()).isEqualTo(user.getEmail());
        assertThat(post.getContent()).isEqualTo(content);
        assertThat(post.getAttachedFile()).isEqualTo(attachedFile);
    }

    // createdAt 자동 추가 테스트
    @Test
    public void CreatedAtTest() {
        // given
        LocalDateTime now = LocalDateTime.now();

        // when
        postRepository.save(Post.builder()
                .title(title)
                .author(user)
                .content(content)
                .attachedFile(attachedFile)
                .build()
        );

        Post post = postRepository.findAll().get(0);
        LocalDateTime createdAt = post.getCreatedAt();

        // then
        assertThat(createdAt).isAfter(now);
    }

    // modifiedAt 자동 수정
    @Test
    public void ModifiedAtTest() {
        // given
        postRepository.save(Post.builder()
                .title(title)
                .author(user)
                .content(content)
                .attachedFile(attachedFile)
                .build()
        );

        Post post = postRepository.findAll().get(0);
        LocalDateTime before = post.getModifiedAt();

        // when
        post.update("수정 제목", "수정 내용", "수정 파일");
        postRepository.save(post);

        Post modifiedPost = postRepository.findById(post.getId()).orElseThrow();

        // then
        assertThat(modifiedPost.getCreatedAt()).isEqualTo(post.getCreatedAt());
        assertThat(modifiedPost.getModifiedAt()).isAfter(before);
    }
}
