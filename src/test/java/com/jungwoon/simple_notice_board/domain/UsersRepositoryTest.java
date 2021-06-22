package com.jungwoon.simple_notice_board.domain;

import com.jungwoon.simple_notice_board.domain.users.Gender;
import com.jungwoon.simple_notice_board.domain.users.Users;
import com.jungwoon.simple_notice_board.domain.users.UsersRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class UsersRepositoryTest {

    @Autowired
    UsersRepository usersRepository;

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
        usersRepository.deleteAll();
    }

    // 저장한 데이터 불러오는 테스트
    @Test
    public void readUsers() {
        // given
        usersRepository.save(Users.builder()
                .email(email)
                .gender(gender)
                .profileImg(profileImg)
                .address(address)
                .build()
        );

        // when
        List<Users> usersList = usersRepository.findAll();

        // then
        Users users = usersList.get(0);
        assertThat(users.getEmail()).isEqualTo(email);
        assertThat(users.getGender()).isEqualTo(gender);
        assertThat(users.getProfileImg()).isEqualTo(profileImg);
        assertThat(users.getAddress()).isEqualTo(address);
    }

    // 생성 시간 자동 저장 테스트
    @Test
    public void CreatedAtTest() {
        // given
        LocalDateTime now = LocalDateTime.now();

        // when
        usersRepository.save(Users.builder()
                .email(email)
                .gender(gender)
                .profileImg(profileImg)
                .address(address)
                .build()
        );

        List<Users> usersList = usersRepository.findAll();

        Users user = usersList.get(0);

        // then
        assertThat(user.getCreatedAt()).isAfter(now);
    }

    @Test
    public void UpdatedAtTest() {
        // given
        usersRepository.save(Users.builder()
                .email(email)
                .gender(gender)
                .profileImg(profileImg)
                .address(address)
                .build()
        );

        Users user = usersRepository.findAll().get(0);
        LocalDateTime before = user.getModifiedAt();

        // when
        user.update("이미지 수정", "주소 수정");

        usersRepository.save(user);

        LocalDateTime after = usersRepository.findAll().get(0).getModifiedAt();

        // then
        assertThat(after).isAfter(before);
    }
}
