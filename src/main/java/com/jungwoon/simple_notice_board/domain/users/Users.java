package com.jungwoon.simple_notice_board.domain.users;

import com.jungwoon.simple_notice_board.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// 사용자 정보 엔티티
@Getter
@NoArgsConstructor
@Entity
public class Users extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private Gender gender;

    private String profileImg;

    private String address;

    @Builder
    public Users(String email, Gender gender, String profileImg, String address) {
        this.email = email;
        this.gender = gender;
        this.profileImg = profileImg;
        this.address = address;
    }

    public void update(String profileImg, String address) {
        this.profileImg = profileImg;
        this.address = address;
    }
}
