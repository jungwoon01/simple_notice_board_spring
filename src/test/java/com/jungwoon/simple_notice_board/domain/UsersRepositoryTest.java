package com.jungwoon.simple_notice_board.domain;

import com.jungwoon.simple_notice_board.domain.users.Users;
import com.jungwoon.simple_notice_board.domain.users.UsersRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class UsersRepositoryTest {

    @Autowired
    UsersRepository usersRepository;

    @Test
    public void readUsers() {
        // given
        String email = "test@test.com";
        String gender = "남자";
        String profileImg = "이미지";
        String address = "주소";

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
}
