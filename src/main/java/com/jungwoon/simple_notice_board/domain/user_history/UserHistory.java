package com.jungwoon.simple_notice_board.domain.user_history;

import com.jungwoon.simple_notice_board.domain.BaseTimeEntity;
import com.jungwoon.simple_notice_board.domain.users.User;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@NoArgsConstructor
@Getter
public class UserHistory extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String profileImg;

    private String address;

    @ManyToOne
    private User user;

    @Builder
    public UserHistory(String profileImg, String address, User user) {
        this.profileImg = profileImg;
        this.address = address;
        this.user = user;
    }
}
