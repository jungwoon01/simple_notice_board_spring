package com.jungwoon.simple_notice_board.domain;

import com.jungwoon.simple_notice_board.domain.test_tool.Repositories;
import com.jungwoon.simple_notice_board.domain.user.Gender;
import com.jungwoon.simple_notice_board.domain.user.User;
import com.jungwoon.simple_notice_board.domain.user.UserRepository;
import com.jungwoon.simple_notice_board.domain.user_history.UserHistory;
import com.jungwoon.simple_notice_board.domain.user_history.UserHistoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class UserHistoryRepositoryTest {
    @Autowired
    UserHistoryRepository userHistoryRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    Repositories repositories;

    @BeforeEach
    public void deleteData() {
        repositories.deleteDataOfAllTables();
        repositories.setDummyUsers();
    }

    @Test
    public void readTest() {
        assertThat(userHistoryRepository.findAll().get(0).getUser().getId()).isEqualTo(userRepository.findAll().get(0).getId());
        assertThat(userHistoryRepository.findAll().get(0).getUser().getEmail()).isEqualTo(userRepository.findAll().get(0).getEmail());
    }

    @Test
    public void updateTest() {
        // given
        String img1 = "이미지1";
        String address1 = "주소1";

        User user_1 = userRepository.findAll().get(0);

        String beforeImg = user_1.getProfileImg();
        String beforeAddress = user_1.getAddress();

        // when
        user_1.update(img1, address1);
        userRepository.save(user_1);

        List<UserHistory> userHistories = userHistoryRepository.findByUserId(user_1.getId());
        UserHistory history_1 = userHistories.get(0);
        UserHistory history_2 = userHistories.get(1);

        // then
        assertThat(history_1.getProfileImg()).isEqualTo(beforeImg);
        assertThat(history_1.getAddress()).isEqualTo(beforeAddress);
        assertThat(history_2.getProfileImg()).isEqualTo(img1);
        assertThat(history_2.getAddress()).isEqualTo(address1);
    }
}
