package com.jungwoon.simple_notice_board.domain;

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

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    Repositories repositories;

    @BeforeEach
    private void setUserData() {
        repositories.deleteDataOfAllTables();
        repositories.setDummyUsers();
    }

    @Test
    public void readTest() {
        User user = userRepository.findByEmail(repositories.userList.get(0).getEmail()).orElseThrow();

        postRepository.save(Post.builder()
                .title("제목")
                .author(user)
                .content("내용")
                .attachedFile("첨부파일")
                .build()
        );

        // when
        List<Post> postList = postRepository.findAll();

        // then
        Post post = postList.get(0);
        assertThat(post.getTitle()).isEqualTo("제목");
        assertThat(post.getAuthor().getEmail()).isEqualTo(user.getEmail());
        assertThat(post.getContent()).isEqualTo("내용");
        assertThat(post.getAttachedFile()).isEqualTo("첨부파일");
    }

    // createdAt 자동 추가 테스트
    @Test
    public void CreatedAtTest() {
        // given
        LocalDateTime now = LocalDateTime.now().minusSeconds(1L);

        // when
        User user = userRepository.findByEmail(repositories.userList.get(0).getEmail()).orElseThrow();

        postRepository.save(Post.builder()
                .title("제목")
                .author(user)
                .content("내용")
                .attachedFile("첨부파일")
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
        User user = userRepository.findByEmail(repositories.userList.get(0).getEmail()).orElseThrow();

        postRepository.save(Post.builder()
                .title("제목")
                .author(user)
                .content("내용")
                .attachedFile("첨부파일")
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
