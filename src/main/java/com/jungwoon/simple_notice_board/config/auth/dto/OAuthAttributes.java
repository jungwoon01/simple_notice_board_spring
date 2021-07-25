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
    private String attributesId;
    private String email;
    private String registrationId;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String attributesId, String email, String registrationId) {
        this.attributes = attributes;
        this.attributesId = attributesId;
        this.email = email;
        this.registrationId = registrationId;
    }

    public static OAuthAttributes of(String registrationId, Map<String, Object> attributes) {
        // 카카오 로그인인지
        if("naver".equals(registrationId)) {
            return ofNaver(registrationId, attributes);
        }

        // 네이버 로그인인지
        if("kakao".equals(registrationId)) {
            return ofKakao(registrationId, attributes);
        }

        return ofGoogle(registrationId, attributes);
    }

    // 구글 attributes 리턴
    private static OAuthAttributes ofGoogle(String registrationId, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .attributesId((String)attributes.get("sub"))
                .email((String) attributes.get("email"))
                .registrationId(registrationId)
                .attributes(attributes)
                .build();
    }

    // 네이버 attributes 리턴
    private static OAuthAttributes ofNaver(String registrationId, Map<String, Object> attributes) {
        String attributesId = (String) attributes.get("id");
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .attributesId(attributesId)
                .email((String) response.get("email"))
                .registrationId(registrationId)
                .attributes(response)
                .build();
    }

    // 카카오 attributes 리턴
    private static OAuthAttributes ofKakao(String registrationId, Map<String, Object> attributes) {
        String attributesId = String.valueOf(attributes.get("id"));
        Map<String,Object> response = (Map<String, Object>)attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) response.get("profile");

        return OAuthAttributes.builder()
                .attributesId(attributesId)
                .email((String) profile.get("email"))
                .registrationId(registrationId)
                .attributes(attributes)
                .build();
    }

    public User toUserEntity() {
        return User.builder()
                .attributesId(attributesId)
                .registrationId(registrationId)
                .email(email)
                .role(Role.VISITOR)
                .build();
    }
}
