package com.jungwoon.simple_notice_board.domain;

import com.jungwoon.simple_notice_board.domain.test_tool.Repositories;
import com.jungwoon.simple_notice_board.domain.user.User;
import com.jungwoon.simple_notice_board.domain.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import javax.annotation.security.RunAs;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    Repositories repositories;

    @BeforeEach
    public void setDate() {
        repositories.deleteDataOfAllTables();
        repositories.setDummyUsers();
    }

    // 저장한 데이터 불러오는 테스트
    @Test
    public void readUsers() {
        // when
        List<User> userList = userRepository.findAll();
        User user = userList.get(0);

        // then
        assertThat(user.getEmail()).isEqualTo(repositories.userList.get(0).getEmail());
        assertThat(user.getProfileImg()).isEqualTo(repositories.userList.get(0).getProfileImg());
        assertThat(user.getAddress()).isEqualTo(repositories.userList.get(0).getAddress());
    }

    // 생성 시간 자동 저장 테스트
    @Test
    public void CreatedAtTest() {
        // given
        LocalDateTime now = LocalDateTime.now().minusSeconds(10L);

        List<User> userList = userRepository.findAll();

        User user = userList.get(0);

        // then
        assertThat(user.getCreatedAt()).isAfter(now);
    }

    @Test
    public void UpdatedAtTest() {
        // given
        User user = userRepository.findAll().get(0);
        LocalDateTime before = user.getModifiedAt();

        // when
        user.update("이미지 수정", "주소 수정");

        userRepository.save(user);

        LocalDateTime after = userRepository.findAll().get(0).getModifiedAt();

        // then
        assertThat(after).isAfter(before);
    }
}
