package com.adarsh.backend.feature.auth.infrastructure.auth;

import com.adarsh.backend.feature.user.application.port.UserCommandRepository;
import com.adarsh.backend.feature.user.domain.model.AuthProvider;
import com.adarsh.backend.feature.user.domain.model.Role;
import com.adarsh.backend.feature.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class OAuth2UserService extends DefaultOAuth2UserService {
    private final UserCommandRepository userCommandRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // 1. Fetch user attributes form the Identity Provider(Google, Github...)

        OAuth2User oAuth2User = super.loadUser(userRequest);

        // Extract attributes and save/update database
        return processOAuth2User(oAuth2User);
    }

    private OAuth2User processOAuth2User(OAuth2User oAuth2User) {
        Map<String, Object> attributes = oAuth2User.getAttributes();

        // Extract standard attributes
        String email = (String) attributes.get("email");
        String name = (String) attributes.get("name");
        String profileImage = (String) attributes.get("profileImage");

        if (email == null) {
            throw new OAuth2AuthenticationException("Email not found  from OAuth2 provider");
        }

        userCommandRepository.findByEmail(email).orElseGet(() -> {
            User newUser = new User.Builder().
                    name(name).email(email).role(Role.USER).provider(AuthProvider.GOOGLE).profileImageUrl(profileImage).build();

            return userCommandRepository.save(newUser);
        });

        return oAuth2User;
    }
}
