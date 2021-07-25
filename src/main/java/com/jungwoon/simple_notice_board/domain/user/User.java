package com.jungwoon.simple_notice_board.domain.user;

import com.jungwoon.simple_notice_board.domain.BaseTimeEntity;
import com.jungwoon.simple_notice_board.domain.user_history.UserHistory;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
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

    // oAuth attributes Id
    @Column(nullable = false)
    private String attributesId;

    // 이메일
    @Column(nullable = false)
    private String email;

    // 닉네임
    private String nickname;

    // 성별
    @Enumerated(EnumType.STRING)
    private Gender gender;

    // 프로필 사진
    private String profileImg;

    // 주소
    private String address;

    // 히스토리
    @OneToMany
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @ToString.Exclude
    private List<UserHistory> userHistories;

    // 권한
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    // 로그인 api 사이트
    @Column(nullable = false)
    private String registrationId;

    @Builder
    public User(String attributesId, String email, String nickname, Gender gender, String profileImg, String address, Role role, String registrationId) {
        this.attributesId = attributesId;
        this.email = email;
        this.nickname = nickname;
        this.gender = gender;
        this.profileImg = profileImg;
        this.address = address;
        this.role = role;
        this.registrationId = registrationId;
    }

    public void update(String profileImg, String address) {
        this.profileImg = profileImg;
        this.address = address;
    }

    public User profileImgUpdate(String profileImg) {
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
