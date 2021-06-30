package com.jungwoon.simple_notice_board.web.dto.user;

import com.jungwoon.simple_notice_board.domain.users.User;
import lombok.Getter;

@Getter
public class UserListDto {
    private Long id;
    private String email;
    private String gender;
    private String profileImg;
    private String address;
    private String role;
    private String registrationId;
    private String site;
    private String createdAt;

    public UserListDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.gender = user.getGender().getTitle();
        this.profileImg = user.getProfileImg();
        this.address = user.getAddress();
        this.role = user.getRole().getTitle();
        this.site = user.getRegistrationId();
        this.createdAt = user.getCreatedAt().toString();
        this.registrationId = user.getRegistrationId();
    }
}
