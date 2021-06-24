package com.jungwoon.simple_notice_board.domain;

import com.jungwoon.simple_notice_board.domain.user_history.UserHistoryRepository;
import com.jungwoon.simple_notice_board.domain.users.Gender;
import com.jungwoon.simple_notice_board.domain.users.User;
import com.jungwoon.simple_notice_board.domain.users.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class UserHistoryRepositoryTest {
    @Autowired
    UserHistoryRepository userHistoryRepository;
    @Autowired
    UserRepository userRepository;

    @AfterEach
    public void deleteData() {
        userHistoryRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void saveTest() {
        // when
        User user = userRepository.save(User.builder()
                .email("email@naver.com")
                .address("주소")
                .profileImg("이미지")
                .gender(Gender.MALE)
                .build());

        // then
        assertThat(userHistoryRepository.findAll().get(0).getUser().getId())
                .isEqualTo(user.getId());
    }

    @Test
    public void updateTest() {
        // given
        String img1 = "이미지1";
        String img2 = "이미지2";
        String img3 = "이미지3";
        String address1 = "주소1";
        String address2 = "주소2";
        String address3 = "주소3";

        User user1 = userRepository.save(User.builder()
                .email("email@naver.com")
                .profileImg(img1)
                .address(address1)
                .gender(Gender.MALE)
                .build());

        // when
        user1.update(img2, address2);
        User user2 = userRepository.save(user1);

        user2.update(img3, address3);
        userRepository.save(user2);

        userHistoryRepository.findAll().forEach(System.out::println);

        // then
        checkHistory(0, user1.getId(), img1, address1);
        checkHistory(1, user1.getId(), img2, address2);
        checkHistory(2, user1.getId(), img3, address3);
    }

    public void checkHistory(int index, Long id, String profileImg, String address) {
        // 예상 이미지와 히스토리 내용 일치하는지 검사
        assertThat(userHistoryRepository.findByUserId(id).get(index).getProfileImg()).isEqualTo(profileImg);
        // 예상 주소와 히스토리 내용 일치하는지 검사
        assertThat(userHistoryRepository.findByUserId(id).get(index).getAddress()).isEqualTo(address);
    }
}
