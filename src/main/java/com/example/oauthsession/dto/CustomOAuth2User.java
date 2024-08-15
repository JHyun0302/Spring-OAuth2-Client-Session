package com.example.oauthsession.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

@RequiredArgsConstructor
public class CustomOAuth2User implements OAuth2User {

    private final OAuth2Response oAuth2Response;

    private final String role;

    //리소스 서버로부터 넘어오는 모든 데이터
    @Override
    public Map<String, Object> getAttributes() {
        return null; // (네이버, 구글)의 key가 달라서 null 처리
    }

    //role
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return role;
            }
        });

        return collection;
    }

    //사용자 별명/이름
    @Override
    public String getName() {
        return oAuth2Response.getName();
    }

    public String getUsername() {
        return oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();
    }
}
