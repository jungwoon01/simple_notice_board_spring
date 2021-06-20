package com.jungwoon.simple_notice_board.domain;

import com.jungwoon.simple_notice_board.domain.posts.Posts;
import com.jungwoon.simple_notice_board.domain.posts.PostsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @Test
    public void get() {
        // given
        String author = "작성자";
        String title = "제목";
        String content = "내용";
        String attachedFile = "첨부파일";

        postsRepository.save(Posts.builder()
                .title(title)
                .author(author)
                .content(content)
                .attachedFile(attachedFile)
                .build()
        );

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getAuthor()).isEqualTo(author);
        assertThat(posts.getContent()).isEqualTo(content);
        assertThat(posts.getAttachedFile()).isEqualTo(attachedFile);
    }
}
