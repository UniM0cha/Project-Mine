package com.project.mine.auth.userinfo;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SuppressWarnings("unchecked")
public class KakaoUserInfo implements OAuth2UserInfo {
    private Map<String, Object> attributes;
    private Map<String, Object> attributesAccount;
    private Map<String, Object> attributesProfile;

    public KakaoUserInfo(Map<String, Object> attributes) {
        /*
         * System.out.println(attributes);
         * {id=아이디값,
         * connected_at=2022-02-22T15:50:21Z,
         * properties={nickname=이름},
         * kakao_account={
         * profile_nickname_needs_agreement=false,
         * profile={nickname=이름},
         * has_email=true,
         * email_needs_agreement=false,
         * is_email_valid=true,
         * is_email_verified=true,
         * email=이메일}
         * }
         */
        // log.info("===========> attributes: " + attributes);

        this.attributes = attributes;
        this.attributesAccount = (Map<String, Object>) attributes.get("kakao_account");
        this.attributesProfile = (Map<String, Object>) attributesAccount.get("profile");
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getProvider() {
        return "Kakao";
    }

    @Override
    public String getProviderId() {
        return attributes.get("id").toString();
    }

    @Override
    public String getEmail() {
        try {
            return attributesAccount.get("email").toString();
        } catch (NullPointerException e) {
            log.info("이메일 수집 거부");
            return null;
        }
    }

    @Override
    public String getName() {
        return attributesProfile.get("nickname").toString();
    }

    @Override
    public String getProfileImg() {
        try {
            return attributesProfile.get("profile_image_url").toString();
        } catch (NullPointerException e) {
            log.info("프로필 사진 수집 거부");
            return null;
        }
    }

}
