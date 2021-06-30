package com.jungwoon.simple_notice_board.service.user;

import com.jungwoon.simple_notice_board.domain.users.User;
import com.jungwoon.simple_notice_board.domain.users.UserRepository;
import com.jungwoon.simple_notice_board.web.dto.user.UserListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public List<UserListDto> findAll() {
        return userRepository.findAll().stream()
                .map(UserListDto::new)
                .collect(Collectors.toList());
    }
}
