package com.jungwoon.simple_notice_board.domain;

import com.jungwoon.simple_notice_board.domain.posts.Posts;
import com.jungwoon.simple_notice_board.domain.posts.PostsRepository;
import com.jungwoon.simple_notice_board.domain.users.Users;
import com.jungwoon.simple_notice_board.domain.users.UsersRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @Autowired
    UsersRepository usersRepository;

    Users user;

    // 테스트에 필요한 테이터 세팅
    @BeforeEach
    public void setUser() {
        user = Users.builder()
                .email("이메일")
                .gender("남자")
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
        // given
        String title = "제목";
        String content = "내용";
        String attachedFile = "첨부파일";

        postsRepository.save(Posts.builder()
                .title(title)
                .user(user)
                .content(content)
                .attachedFile(attachedFile)
                .build()
        );

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getUser().getEmail()).isEqualTo(user.getEmail());
        assertThat(posts.getContent()).isEqualTo(content);
        assertThat(posts.getAttachedFile()).isEqualTo(attachedFile);
    }
}
