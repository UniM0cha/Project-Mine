package com.project.mine.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import com.project.mine.auth.userinfo.OAuth2UserInfo;
import com.project.mine.domain.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class PrincipalDetails implements OAuth2User {

    private User user;
    // private Map<String, Object> attributes;
    private OAuth2UserInfo oAuth2UserInfo;

    public PrincipalDetails(User user, OAuth2UserInfo oAuth2UserInfo) {
        this.user = user;
        // this.attributes = attributes;
        this.oAuth2UserInfo = oAuth2UserInfo;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oAuth2UserInfo.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(
                new GrantedAuthority() {
                    @Override
                    public String getAuthority() {
                        return user.getRole().toString();
                    }
                });
        return collect;
    }

    @Override
    public String getName() {
        return user.getId().toString();
    }

}
