package com.jungwoon.simple_notice_board.config.auth.dto;

import com.jungwoon.simple_notice_board.domain.user.Gender;
import com.jungwoon.simple_notice_board.domain.user.Role;
import com.jungwoon.simple_notice_board.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String email;
    private String picture;
    private String registrationId;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String email, String picture, String registrationId) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.email = email;
        this.picture = picture;
        this.registrationId = registrationId;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        // 네이버 로그인이면 userNameAttributeName 을 id로 지정
        if("naver".equals(registrationId)) {
            return ofNaver(registrationId,"id", attributes);
        }

        // 카카오 로그인이면 userNameAttributeName 을 id로 지정
        if("kakao".equals(registrationId)) {
            return ofKakao(registrationId,"id", attributes);
        }

        return ofGoogle(registrationId, userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .registrationId(registrationId)
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofNaver(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .email((String) response.get("email"))
                .picture((String) response.get("profile_image"))
                .registrationId(registrationId)
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofKakao(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        Map<String,Object> response = (Map<String, Object>)attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) response.get("profile");

        System.out.println(attributes);

        return OAuthAttributes.builder()
                .email((String) response.get("email"))
                .picture((String) profile.get("profile_image_url"))
                .registrationId(registrationId)
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity() {
        return User.builder()
                .email(email)
                .profileImg(picture)
                .role(Role.HOST)
                .gender(Gender.MALE)
                .address("주소다")
                .registrationId(registrationId)
                .build();
    }
}
