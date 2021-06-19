package com.jungwoon.simple_notice_board.domain.users;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String gender;

    private String profileImg;

    private String address;

    @Builder
    public Users(String email, String gender, String profileImg, String address) {
        this.email = email;
        this.gender = gender;
        this.profileImg = profileImg;
        this.address = address;
    }
}
