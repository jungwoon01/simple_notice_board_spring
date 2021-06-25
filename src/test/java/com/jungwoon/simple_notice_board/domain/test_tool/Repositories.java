package com.jungwoon.simple_notice_board.domain.test_tool;

import com.jungwoon.simple_notice_board.domain.likes.LikesRepository;
import com.jungwoon.simple_notice_board.domain.posts.PostRepository;
import com.jungwoon.simple_notice_board.domain.user_history.UserHistoryRepository;
import com.jungwoon.simple_notice_board.domain.users.UserRepository;
import com.jungwoon.simple_notice_board.domain.visits.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public void deleteDataOfAllTables() {
        userHistoryRepository.deleteAll();
        likesRepository.deleteAll();
        visitRepository.deleteAll();
        postRepository.deleteAll();
        userRepository.deleteAll();
    }
}
