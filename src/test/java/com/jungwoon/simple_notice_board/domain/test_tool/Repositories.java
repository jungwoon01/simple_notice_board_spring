package com.jungwoon.simple_notice_board.domain.test_tool;

import com.jungwoon.simple_notice_board.domain.likes.LikesRepository;
import com.jungwoon.simple_notice_board.domain.post.PostRepository;
import com.jungwoon.simple_notice_board.domain.user.Gender;
import com.jungwoon.simple_notice_board.domain.user.Role;
import com.jungwoon.simple_notice_board.domain.user.User;
import com.jungwoon.simple_notice_board.domain.user_history.UserHistoryRepository;
import com.jungwoon.simple_notice_board.domain.user.UserRepository;
import com.jungwoon.simple_notice_board.domain.visit.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Repositories {
    @Autowired
    private LikesRepository likesRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VisitRepository visitRepository;
    @Autowired
    private UserHistoryRepository userHistoryRepository;

    public List<User> userList;

    public void deleteDataOfAllTables() {
        userHistoryRepository.deleteAll();
        likesRepository.deleteAll();
        visitRepository.deleteAll();
        postRepository.deleteAll();
        userRepository.deleteAll();
    }

    public void setDummyUsers() {
        userList = new ArrayList<>();

        userList.add(new User("11111@관리자.com", Gender.MALE, "Image1", "address1", Role.HOST, "test"));
        userList.add(new User("22222@email.com", Gender.FEMALE, "Image2", "address2", Role.USER, "test"));
        userList.add(new User("33333@email.com", Gender.FEMALE, "Image3", "address3", Role.USER, "test"));
        userList.add(new User("44444@email.com", Gender.MALE, "Imag4", "address4", Role.USER, "test"));
        userList.add(new User("55555@email.com", Gender.MALE, "Image5", "address5", Role.USER, "test"));

        userRepository.saveAll(userList);
    }
}
