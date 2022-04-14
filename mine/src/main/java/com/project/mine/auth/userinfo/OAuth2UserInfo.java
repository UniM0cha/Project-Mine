package com.project.mine.auth.userinfo;

import java.util.Map;

public interface OAuth2UserInfo {
    Map<String, Object> getAttributes();

    String getProvider();

    String getProviderId();

    String getEmail();

    String getName();

    String getProfileImg();
}
