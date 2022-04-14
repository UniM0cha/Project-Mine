package com.project.mine.auth;

import com.project.mine.auth.userinfo.KakaoUserInfo;
import com.project.mine.auth.userinfo.OAuth2UserInfo;
import com.project.mine.domain.User;
import com.project.mine.repository.UserRepository;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        OAuth2UserInfo oAuth2UserInfo = null;
        String provider = userRequest.getClientRegistration().getRegistrationId();

        // 혹시라도 나중에 다른 플랫폼을 추가할 수 있으므로 각 클래스별로 나눔
        if (provider.equals("kakao")) {
            oAuth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
        }

        String provider_providerId = provider + "_" + oAuth2UserInfo.getProviderId();
        log.info("provider_providerId: " + provider_providerId);
        String email = oAuth2UserInfo.getEmail();
        log.info("eamil: " + email);
        String profileImg = oAuth2UserInfo.getProfileImg();
        log.info("profileImg: " + profileImg);
        String nickname = oAuth2UserInfo.getName();
        log.info("nickname: " + nickname);

        User userByProviderId = userRepository.findByProviderId(provider_providerId).orElse(null);

        // 회원이 없을 경우 회원가입
        if (userByProviderId == null) {
            User newUser = User.createNewUser(provider_providerId, email, profileImg, nickname);
            User savedUser = userRepository.save(newUser);
            return new PrincipalDetails(savedUser, oAuth2UserInfo);
        }

        return new PrincipalDetails(userByProviderId, oAuth2UserInfo);
    }
}
