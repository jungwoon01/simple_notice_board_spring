package com.jungwoon.simple_notice_board.domain;

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
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Repositories repositories;

    String email;
    Gender gender;
    String profileImg;
    String address;

    @BeforeEach
    public void setDate() {
        email = "test@test.com";
        gender = Gender.MALE;
        profileImg = "이미지";
        address = "주소";
    }

    @AfterEach
    public void deleteData() {
        repositories.deleteDataOfAllTables();
    }

    // 저장한 데이터 불러오는 테스트
    @Test
    public void readUsers() {
        // given
        userRepository.save(User.builder()
                .email(email)
                .gender(gender)
                .profileImg(profileImg)
                .address(address)
                .build()
        );

        // when
        List<User> userList = userRepository.findAll();

        // then
        User user = userList.get(0);
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getGender()).isEqualTo(gender);
        assertThat(user.getProfileImg()).isEqualTo(profileImg);
        assertThat(user.getAddress()).isEqualTo(address);
    }

    // 생성 시간 자동 저장 테스트
    @Test
    public void CreatedAtTest() {
        // given
        LocalDateTime now = LocalDateTime.now().minusSeconds(1L);

        // when
        userRepository.save(User.builder()
                .email(email)
                .gender(gender)
                .profileImg(profileImg)
                .address(address)
                .build()
        );

        List<User> userList = userRepository.findAll();

        User user = userList.get(0);

        // then
        assertThat(user.getCreatedAt()).isAfter(now);
    }

    @Test
    public void UpdatedAtTest() {
        // given
        userRepository.save(User.builder()
                .email(email)
                .gender(gender)
                .profileImg(profileImg)
                .address(address)
                .build()
        );

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
