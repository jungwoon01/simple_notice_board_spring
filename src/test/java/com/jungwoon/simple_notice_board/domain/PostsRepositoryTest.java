package com.jungwoon.simple_notice_board.domain;

import com.jungwoon.simple_notice_board.domain.posts.Posts;
import com.jungwoon.simple_notice_board.domain.posts.PostsRepository;
import com.jungwoon.simple_notice_board.domain.users.Gender;
import com.jungwoon.simple_notice_board.domain.users.Users;
import com.jungwoon.simple_notice_board.domain.users.UsersRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @Autowired
    UsersRepository usersRepository;

    Users user;

    // given
    String title = "제목";
    String content = "내용";
    String attachedFile = "첨부파일";

    // 테스트에 필요한 테이터 세팅
    @BeforeEach
    public void setUser() {
        user = Users.builder()
                .email("이메일")
                .gender(Gender.MALE)
                .profileImg("사진")
                .address("주소")
                .build();

        usersRepository.save(user);
    }

    // 사용한 테이블 데이터 모두 지우기
    @AfterEach
    public void deleteData() {
        postsRepository.deleteAll();
        usersRepository.deleteAll();
    }

    @Test
    public void get() {
        postsRepository.save(Posts.builder()
                .title(title)
                .author(user)
                .content(content)
                .attachedFile(attachedFile)
                .build()
        );

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getAuthor().getEmail()).isEqualTo(user.getEmail());
        assertThat(posts.getContent()).isEqualTo(content);
        assertThat(posts.getAttachedFile()).isEqualTo(attachedFile);
    }

    // createdAt 자동 추가 테스트
    @Test
    public void CreatedAtTest() {
        // given
        LocalDateTime now = LocalDateTime.now();

        // when
        postsRepository.save(Posts.builder()
                .title(title)
                .author(user)
                .content(content)
                .attachedFile(attachedFile)
                .build()
        );

        Posts post = postsRepository.findAll().get(0);
        LocalDateTime createdAt = post.getCreatedAt();

        // then
        assertThat(createdAt).isAfter(now);
    }

    // modifiedAt 자동 수정
    @Test
    public void ModifiedAtTest() {
        // given
        postsRepository.save(Posts.builder()
                .title(title)
                .author(user)
                .content(content)
                .attachedFile(attachedFile)
                .build()
        );

        Posts post = postsRepository.findAll().get(0);
        LocalDateTime before = post.getModifiedAt();

        // when
        post.update("수정 제목", "수정 내용", "수정 파일");
        postsRepository.save(post);

        Posts modifiedPost = postsRepository.findById(post.getId()).orElseThrow();

        // then
        assertThat(modifiedPost.getCreatedAt()).isEqualTo(post.getCreatedAt());
        assertThat(modifiedPost.getModifiedAt()).isAfter(before);
    }
}
