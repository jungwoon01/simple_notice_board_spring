package com.jungwoon.simple_notice_board.domain.users;

import com.jungwoon.simple_notice_board.domain.user_history.UserHistory;
import com.jungwoon.simple_notice_board.domain.user_history.UserHistoryRepository;
import com.jungwoon.simple_notice_board.support.BeanUtils;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

public class UserEntityListener {
    @PostPersist
    @PostUpdate
    public void addUserHistory(Object object) {
        UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);

        User user = (User) object;

        userHistoryRepository.save(
                UserHistory.builder()
                .profileImg(user.getProfileImg())
                .address(user.getAddress())
                .user(user)
                .build()
        );
    }
}
