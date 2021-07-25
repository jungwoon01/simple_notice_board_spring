package com.jungwoon.simple_notice_board.config.oauth;

import com.jungwoon.simple_notice_board.config.auth.dto.OAuthAttributes;
import com.jungwoon.simple_notice_board.domain.user.User;
import com.jungwoon.simple_notice_board.domain.user.UserRepository;
import com.jungwoon.simple_notice_board.handler.exception.OAuthIdDuplicateException;
import lombok.*;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class CustomOAuthUserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        String registrationId = userRequest.getClientRegistration().getRegistrationId(); // 가입 사이트 식별
        Map<String, Object> oAuthAttr = super.loadUser(userRequest).getAttributes(); // 넘어온 유저 정보 map

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, oAuthAttr); // 필요한 것만 정리된 attributes

        User userEntity = userRepository.findByAttributesId(attributes.getAttributesId()); // attributes 정보로 유저 찾기

        // 처음 로그인을 시도하는 유저
        if(userEntity == null) {
            return new PrincipalDetails(userRepository.save(attributes.toUserEntity()), oAuthAttr);
        }

        // 한번이라도 로그인을 시도했던 유저
        return new PrincipalDetails(userEntity, oAuthAttr);
    }
}
