package com.jungwoon.simple_notice_board.domain.users;

import com.jungwoon.simple_notice_board.domain.BaseTimeEntity;
import com.jungwoon.simple_notice_board.domain.user_history.UserHistory;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// 사용자 정보 엔티티
@NoArgsConstructor
@Entity
@Getter
@ToString(callSuper = true)
@EntityListeners(UserEntityListener.class)
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private Gender gender;

    private String profileImg;

    private String address;

    @OneToMany
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @ToString.Exclude
    private List<UserHistory> userHistories;

    @Builder
    public User(String email, Gender gender, String profileImg, String address) {
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
